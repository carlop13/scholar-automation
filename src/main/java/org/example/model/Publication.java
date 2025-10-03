package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Template for a single post found in search results.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Publication {
    @JsonProperty("title")
    private String title;
    @JsonProperty("publication_info")
    private PublicationInfo publicationInfo;
    @JsonProperty("cited_by")
    private CitedBy citedBy;

    // Getters y Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public PublicationInfo getPublicationInfo() { return publicationInfo; }
    public void setPublicationInfo(PublicationInfo publicationInfo) { this.publicationInfo = publicationInfo; }
    public CitedBy getCitedBy() { return citedBy; }
    public void setCitedBy(CitedBy citedBy) { this.citedBy = citedBy; }
}