package com.customer.api;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private Integer incident;
    private String summary;
    private String dealerId;
    private String dealerName;
    private String userName;
    private String userId;
    private String category;
    private String priority;
    private String status;
    private String openDate;
    private String lastModificationDate;
    private String description;
    private String reportingMethod;
    private String groupName;
    private String dealId;
    private String contractConversationId;
    private String oldCreditAppCvrsId;
    private String newCreditAppCvrsId;

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportingMethod() {
        return reportingMethod;
    }

    public void setReportingMethod(String reportingMethod) {
        this.reportingMethod = reportingMethod;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getContractConversationId() {
        return contractConversationId;
    }

    public void setContractConversationId(String contractConversationId) {
        this.contractConversationId = contractConversationId;
    }

    public String getOldCreditAppCvrsId() {
        return oldCreditAppCvrsId;
    }

    public void setOldCreditAppCvrsId(String oldCreditAppCvrsId) {
        this.oldCreditAppCvrsId = oldCreditAppCvrsId;
    }

    public String getNewCreditAppCvrsId() {
        return newCreditAppCvrsId;
    }

    public void setNewCreditAppCvrsId(String newCreditAppCvrsId) {
        this.newCreditAppCvrsId = newCreditAppCvrsId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
}
