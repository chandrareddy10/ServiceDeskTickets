package com.customer.api;

import java.sql.Blob;

public class Attachment {

    private String docName;
    private String description;
    private String attachedTimeStamp;
    private String attachedBy;
    private Blob document;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachedTimeStamp() {
        return attachedTimeStamp;
    }

    public void setAttachedTimeStamp(String attachedTimeStamp) {
        this.attachedTimeStamp = attachedTimeStamp;
    }

    public String getAttachedBy() {
        return attachedBy;
    }

    public void setAttachedBy(String attachedBy) {
        this.attachedBy = attachedBy;
    }


    public Blob getDocument() {
        return document;
    }

    public void setDocument(Blob document) {
        this.document = document;
    }
}
