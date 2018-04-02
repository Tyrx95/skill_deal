package com.skilldealteam.skilldeal.helpers;

public class ErrorMessage {

    private String description;
    private String targetField;

    public ErrorMessage() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetField() {
        return targetField;
    }

    public void setTargetField(String targetField) {
        this.targetField = targetField;
    }
}
