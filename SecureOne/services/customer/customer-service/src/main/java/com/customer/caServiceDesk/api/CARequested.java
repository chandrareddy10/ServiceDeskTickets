package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CARequested {

    @JsonProperty("@REL_ATTR")
    private String relativeAttribute = "45B8912687C6F14D838D9FE23CB20A23";

    public String getRelativeAttribute() {
        return relativeAttribute;
    }

    public void setRelativeAttribute(String relativeAttribute) {
        this.relativeAttribute = relativeAttribute;
    }
}
