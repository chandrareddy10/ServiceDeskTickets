package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentLink {
    private RequestLink link;

    @JsonProperty("link")
    public RequestLink getLink() {
        return link;
    }

    public void setLink(RequestLink link) {
        this.link = link;
    }
}
