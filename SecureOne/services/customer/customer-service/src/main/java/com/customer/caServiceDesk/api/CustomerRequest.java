package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {

    @JsonProperty("@REL_ATTR")
    private String relativeAttribute = "078C799B7E096F4AB0E4A5515F44DD2E";

    public String getRelativeAttribute() {
        return relativeAttribute;
    }

    public void setRelativeAttribute(String relativeAttribute) {
        this.relativeAttribute = relativeAttribute;
    }
}
