package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIncident {
    @JsonProperty("description")
    private String description;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("z_dealer_id")
    private String dealerId;
    @JsonProperty("z_deal_number")
    private String dealNumber;
    @JsonProperty("z_contract_conversation_id")
    private String contractConversationId;
    @JsonProperty("z_old_credit_app_cvrs_id")
    private String oldCreditAppCvrsId;
    @JsonProperty("z_new_credit_app_cvrs_id")
    private String newCreditAppCvrsId;
    @JsonProperty("status")
    private CAStatusRequest caStatus;
    @JsonProperty("priority")
    private CAPriorityRequest caPriority;
    @JsonProperty("category")
    private CACategoryRequest caCategory;
    @JsonProperty("zreporting_method")
    private CAReportingRequest caReporting;
    @JsonProperty("group")
    private CAGroupRequest caGroup;
    @JsonProperty("requested_by")
    private CARequested caRequested;
    @JsonProperty("customer")
    private CustomerRequest customer;

    public CustomerRequest getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequest customer) {
        this.customer = customer;
    }

    public CARequested getCaRequested() {
        return caRequested;
    }

    public void setCaRequested(CARequested caRequested) {
        this.caRequested = caRequested;
    }

    public CAGroupRequest getCaGroup() {
        return caGroup;
    }

    public void setCaGroup(CAGroupRequest caGroup) {
        this.caGroup = caGroup;
    }

    public CAReportingRequest getCaReporting() {
        return caReporting;
    }

    public void setCaReporting(CAReportingRequest caReporting) {
        this.caReporting = caReporting;
    }

    public CACategoryRequest getCaCategory() {
        return caCategory;
    }

    public void setCaCategory(CACategoryRequest caCategory) {
        this.caCategory = caCategory;
    }

    public CAPriorityRequest getCaPriority() {
        return caPriority;
    }

    public void setCaPriority(CAPriorityRequest caPriority) {
        this.caPriority = caPriority;
    }

    public CAStatusRequest getCaStatus() {
        return caStatus;
    }

    public void setCaStatus(CAStatusRequest caStatus) {
        this.caStatus = caStatus;
    }

    public String getNewCreditAppCvrsId() {
        return newCreditAppCvrsId;
    }

    public void setNewCreditAppCvrsId(String newCreditAppCvrsId) {
        this.newCreditAppCvrsId = newCreditAppCvrsId;
    }

    public String getOldCreditAppCvrsId() {
        return oldCreditAppCvrsId;
    }

    public void setOldCreditAppCvrsId(String oldCreditAppCvrsId) {
        this.oldCreditAppCvrsId = oldCreditAppCvrsId;
    }

    public String getContractConversationId() {
        return contractConversationId;
    }

    public void setContractConversationId(String contractConversationId) {
        this.contractConversationId = contractConversationId;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
