package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Incident {

    private String description;
    private Integer id;
    private String summary;
    private String dealerId;
    private CAStatus caStatus;
    private CAPriority caPriority;
    private CACategory caCategory;
    private CAReporting caReporting;
    private CAGroup caGroup;
    private ActivityLog activityLog;
    private AttachmentLink attachmentLink;
    private Customer customer;
    private Date openDate;
    private Date lastModifiedDate;

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonProperty("@id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("z_dealer_id")
    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    @JsonProperty("status")
    public CAStatus getCaStatus() {
        return caStatus;
    }

    public void setCaStatus(CAStatus caStatus) {
        this.caStatus = caStatus;
    }
    @JsonProperty("priority")
    public CAPriority getCaPriority() {
        return caPriority;
    }

    public void setCaPriority(CAPriority caPriority) {
        this.caPriority = caPriority;
    }
    @JsonProperty("category")
    public CACategory getCaCategory() {
        return caCategory;
    }

    public void setCaCategory(CACategory caCategory) {
        this.caCategory = caCategory;
    }
    @JsonProperty("zreporting_method")
    public CAReporting getCaReporting() {
        return caReporting;
    }

    public void setCaReporting(CAReporting caReporting) {
        this.caReporting = caReporting;
    }
    @JsonProperty("group")
    public CAGroup getCaGroup() {
        return caGroup;
    }

    public void setCaGroup(CAGroup caGroup) {
        this.caGroup = caGroup;
    }
    @JsonProperty("open_date")
    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonProperty("last_mod_dt")
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonProperty("act_log")
    public ActivityLog getActivityLog() {
        return activityLog;
    }

    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }

    @JsonProperty("attachments")
    public AttachmentLink getAttachmentLink() {
        return attachmentLink;
    }

    public void setAttachmentLink(AttachmentLink attachmentLink) {
        this.attachmentLink = attachmentLink;
    }
}
