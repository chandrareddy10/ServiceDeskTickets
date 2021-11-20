package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CAGroupRequest {
    @JsonProperty("@REL_ATTR")
    private String relativeAttribute = "812455410B6E9948804F76E59C64E0D3";

    public String getRelativeAttribute() {
        return relativeAttribute;
    }

    public void setRelativeAttribute(String relativeAttribute) {
        this.relativeAttribute = relativeAttribute;
    }
}
