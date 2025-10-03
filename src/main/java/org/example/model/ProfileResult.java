package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Template for a single author profile result.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileResult {
    @JsonProperty("name")
    private String name;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("affiliations")
    private String affiliations;

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAuthorId() { return authorId; }
    public void setAuthorId(String authorId) { this.authorId = authorId; }
    public String getAffiliations() { return affiliations; }
    public void setAffiliations(String affiliations) { this.affiliations = affiliations; }
}