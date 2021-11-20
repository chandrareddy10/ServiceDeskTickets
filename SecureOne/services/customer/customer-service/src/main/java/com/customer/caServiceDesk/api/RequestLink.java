package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLink {

    private String href;

    @JsonProperty("@href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
