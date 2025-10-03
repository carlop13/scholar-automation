package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    @JsonProperty("name")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}