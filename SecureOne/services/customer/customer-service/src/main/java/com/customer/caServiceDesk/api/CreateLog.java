package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLog {
    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private CreateLogStatus createLogStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CreateLogStatus getCreateLogStatus() {
        return createLogStatus;
    }

    public void setCreateLogStatus(CreateLogStatus createLogStatus) {
        this.createLogStatus = createLogStatus;
    }
}
