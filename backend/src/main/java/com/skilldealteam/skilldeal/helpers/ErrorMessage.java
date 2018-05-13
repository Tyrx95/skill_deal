package com.skilldealteam.skilldeal.helpers;

public class ErrorMessage {

    private String description;
    private String targetField;

    public ErrorMessage() {
    }

    public String getDescription() {
        return description;
    }

    public ErrorMessage setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTargetField() {
        return targetField;
    }

    public ErrorMessage setTargetField(String targetField) {
        this.targetField = targetField;
        return this;
    }
}
