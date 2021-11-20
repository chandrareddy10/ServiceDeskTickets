package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIncidentRequest {

    @JsonProperty("in")
    private CreateIncident createIncident;

    public CreateIncident getCreateIncident() {
        return createIncident;
    }

    public void setCreateIncident(CreateIncident createIncident) {
        this.createIncident = createIncident;
    }
}
