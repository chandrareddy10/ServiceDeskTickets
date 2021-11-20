package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CACategoryRequest {

    @JsonProperty("@REL_ATTR")
    private String relativeAttribute = "pcat:400162";

    public String getRelativeAttribute() {
        return relativeAttribute;
    }

    public void setRelativeAttribute(String relativeAttribute) {
        this.relativeAttribute = relativeAttribute;
    }
}
