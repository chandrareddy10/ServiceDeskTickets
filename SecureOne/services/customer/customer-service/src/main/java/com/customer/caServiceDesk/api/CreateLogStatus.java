package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLogStatus {
    @JsonProperty("@REL_ATTR")
    private String relativeAttribute;

    public String getRelativeAttribute() {
        return relativeAttribute;
    }

    public void setRelativeAttribute(String relativeAttribute) {
        this.relativeAttribute = relativeAttribute;
    }
}
