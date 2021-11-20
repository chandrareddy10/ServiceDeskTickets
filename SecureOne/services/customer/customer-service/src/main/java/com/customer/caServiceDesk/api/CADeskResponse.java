package com.customer.caServiceDesk.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CADeskResponse {


    private CollectionIn collectionIn;

    @JsonProperty("collection_in")
    public CollectionIn getCollectionIn() {
        return collectionIn;
    }

    public void setCollectionIn(CollectionIn collectionIn) {
        this.collectionIn = collectionIn;
    }
}
