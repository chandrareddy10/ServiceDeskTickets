package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessKey {
    private RestAccess rest_access;

    public RestAccess getRest_access() {
        return rest_access;
    }

    public void setRest_access(RestAccess rest_access) {
        this.rest_access = rest_access;
    }
}
