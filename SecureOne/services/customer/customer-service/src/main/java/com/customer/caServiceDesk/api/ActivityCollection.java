package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityCollection {
    private List<ActivityEntity> activityEntityList;


    @JsonProperty("alg")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<ActivityEntity> getActivityEntityList() {
        return activityEntityList;
    }

    public void setActivityEntityList(List<ActivityEntity> activityEntityList) {
        this.activityEntityList = activityEntityList;
    }
}
