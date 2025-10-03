package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Publication;
import org.example.view.ConsoleView;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Controller
 */
public class ScholarController {

    private final ConsoleView view;
    private final String apiKey;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public ScholarController(ConsoleView view, String apiKey) {
        this.view = view;
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public void findAndDisplayPublications(String authorName) {
        try {
            List<Publication> publications = findAuthorPublications(authorName);
            view.displayPublications(authorName, publications);
        } catch (IOException | InterruptedException e) {
            view.displayError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private List<Publication> findAuthorPublications(String authorName) throws IOException, InterruptedException {
        String query = "author:\"" + authorName + "\"";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

        String url = String.format(
                "https://serpapi.com/search.json?engine=google_scholar&q=%s&api_key=%s",
                encodedQuery, apiKey
        );

        String responseBody = sendRequest(url);
        JsonNode root = mapper.readTree(responseBody);

        if (root.has("organic_results")) {
            Publication[] publications = mapper.treeToValue(root.get("organic_results"), Publication[].class);
            return Arrays.asList(publications);
        }
        return Collections.emptyList();
    }

    private String sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        if (response.statusCode() != 200) {
            throw new IOException("API request failed with status code: " + response.statusCode() + " - Response: " + responseBody);
        }

        JsonNode rootNode = mapper.readTree(responseBody);
        if (rootNode.has("error")) {
            throw new IOException("API returned an error: " + rootNode.get("error").asText());
        }

        return responseBody;
    }
}
