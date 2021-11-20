package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLogResponse {
    private LogResponseEntity logResponseEntity;

    @JsonProperty("cr")
    public LogResponseEntity getLogResponseEntity() {
        return logResponseEntity;
    }

    public void setLogResponseEntity(LogResponseEntity logResponseEntity) {
        this.logResponseEntity = logResponseEntity;
    }
}
