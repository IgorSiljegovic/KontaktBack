package com.combis.kontakti.config;

public class ConstraintMessage {
    private String property;
    private String message;
    private String entity;

    public ConstraintMessage() {
    }

    public ConstraintMessage(String property, String message, String entity) {
        this.property = property;
        this.message = message;
        this.entity = entity;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
