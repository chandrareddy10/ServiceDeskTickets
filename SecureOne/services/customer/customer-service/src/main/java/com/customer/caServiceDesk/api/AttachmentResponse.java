package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentResponse {

    private AttachmentEntity attachmentEntity;

    @JsonProperty("attmnt" )
    public AttachmentEntity getAttachmentEntity() {
        return attachmentEntity;
    }

    public void setAttachmentEntity(AttachmentEntity attachmentEntity) {
        this.attachmentEntity = attachmentEntity;
    }
}
