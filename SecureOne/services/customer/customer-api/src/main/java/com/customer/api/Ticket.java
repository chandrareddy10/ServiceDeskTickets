package com.customer.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {
    private Integer incident;
    private String summary;
    private String dealerId;
    private String dealerName;
    private String userId;
    private String category;
    private String priority;
    private String status;
    private String assignedTo;
    private Date openDate;
    private Date lastModificationDate;
    private List<TicketLog> ticketLogList = new ArrayList<>();
    private List<Attachment> attachments  = new ArrayList<>();

    public Integer getIncident() {
        return incident;
    }

    public void setIncident(Integer incident) {
        this.incident = incident;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }


    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public List<TicketLog> getTicketLogList() {
        return ticketLogList;
    }

    public void setTicketLogList(List<TicketLog> ticketLogList) {
        this.ticketLogList = ticketLogList;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
