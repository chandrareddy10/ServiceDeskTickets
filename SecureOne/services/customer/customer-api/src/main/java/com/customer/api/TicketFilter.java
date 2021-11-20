package com.customer.api;

import java.util.Date;

public class TicketFilter {
    private String searchByDealerId;
    private String searchByFirstName;
    private String searchByLastName;
    private Date searchByStartDate;
    private Date searchByEndDate;
    private String searchByCategory;
    private Integer searchByIncident;
    private String searchBySummary;
    private String searchByPriority;
    private String searchByStatus;
    private String searchByDescription;

    public String getSearchByDealerId() {
        return searchByDealerId;
    }

    public void setSearchByDealerId(String searchByDealerId) {
        this.searchByDealerId = searchByDealerId;
    }

    public Date getSearchByStartDate() {
        return searchByStartDate;
    }

    public void setSearchByStartDate(Date searchByStartDate) {
        this.searchByStartDate = searchByStartDate;
    }

    public Date getSearchByEndDate() {
        return searchByEndDate;
    }

    public void setSearchByEndDate(Date searchByEndDate) {
        this.searchByEndDate = searchByEndDate;
    }

    public String getSearchByCategory() {
        return searchByCategory;
    }

    public void setSearchByCategory(String searchByCategory) {
        this.searchByCategory = searchByCategory;
    }

    public Integer getSearchByIncident() {
        return searchByIncident;
    }

    public void setSearchByIncident(Integer searchByIncident) {
        this.searchByIncident = searchByIncident;
    }

    public String getSearchBySummary() {
        return searchBySummary;
    }

    public void setSearchBySummary(String searchBySummary) {
        this.searchBySummary = searchBySummary;
    }

    public String getSearchByPriority() {
        return searchByPriority;
    }

    public void setSearchByPriority(String searchByPriority) {
        this.searchByPriority = searchByPriority;
    }

    public String getSearchByStatus() {
        return searchByStatus;
    }

    public void setSearchByStatus(String searchByStatus) {
        this.searchByStatus = searchByStatus;
    }


    public String getSearchByDescription() {
        return searchByDescription;
    }

    public void setSearchByDescription(String searchByDescription) {
        this.searchByDescription = searchByDescription;
    }

    public String getSearchByFirstName() {
        return searchByFirstName;
    }

    public void setSearchByFirstName(String searchByFirstName) {
        this.searchByFirstName = searchByFirstName;
    }

    public String getSearchByLastName() {
        return searchByLastName;
    }

    public void setSearchByLastName(String searchByLastName) {
        this.searchByLastName = searchByLastName;
    }
}
