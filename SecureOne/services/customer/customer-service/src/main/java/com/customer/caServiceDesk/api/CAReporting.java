package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CAReporting {

    private String reportingMethod;
    @JsonProperty("@COMMON_NAME")
    public String getReportingMethod() {
        return reportingMethod;
    }

    public void setReportingMethod(String reportingMethod) {
        this.reportingMethod = reportingMethod;
    }
}
