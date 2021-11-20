package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateResponse {
    @JsonProperty("in")
    private CreateIncidentResponse createIncidentResponse;


    public CreateIncidentResponse getCreateIncidentResponse() {
        return createIncidentResponse;
    }

    public void setCreateIncidentResponse(CreateIncidentResponse createIncidentResponse) {
        this.createIncidentResponse = createIncidentResponse;
    }
}
