package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLogRequest {
    @JsonProperty("cr")
    private CreateLog createLog;

    public CreateLog getCreateLog() {
        return createLog;
    }

    public void setCreateLog(CreateLog createLog) {
        this.createLog = createLog;
    }
}
