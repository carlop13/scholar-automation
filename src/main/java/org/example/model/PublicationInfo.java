package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicationInfo {
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("authors")
    private List<Author> authors;

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }
}