package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CADeskResponse {
    private boolean identical;
    private BigDecimal confidence;

    public BigDecimal getConfidence() {
        return confidence;
    }

    public void setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
    }

    @JsonProperty("isIdentical")
    public boolean isIdentical() {
        return identical;
    }

    public void setIdentical(boolean identical) {
        this.identical = identical;
    }
}
