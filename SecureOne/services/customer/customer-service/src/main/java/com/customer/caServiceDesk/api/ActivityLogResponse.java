package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityLogResponse {

    private ActivityCollection activityCollection;

    @JsonProperty("collection_alg" )
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public ActivityCollection getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(ActivityCollection activityCollection) {
        this.activityCollection = activityCollection;
    }
}
