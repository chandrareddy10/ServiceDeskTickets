package com.customer.api;

public class TicketLog {

    private String logType;
    private String createdBy;
    private String description;
    private String createdOn;
    private String statusRequested;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }


    public String getStatusRequested() {
        return statusRequested;
    }

    public void setStatusRequested(String statusRequested) {
        this.statusRequested = statusRequested;
    }
}
