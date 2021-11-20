package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIncidentResponse {

    @JsonProperty("@id")
    private Integer incident;


    public Integer getIncident() {
        return incident;
    }

    public void setIncident(Integer incident) {
        this.incident = incident;
    }
}
