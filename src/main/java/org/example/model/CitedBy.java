package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitedBy {
    @JsonProperty("total")
    private int total;

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}