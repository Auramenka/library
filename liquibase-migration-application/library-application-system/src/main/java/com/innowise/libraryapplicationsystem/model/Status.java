package com.innowise.libraryapplicationsystem.model;

public enum Status {

    ACTIVE("active"),
    CANCELED("canceled"),
    COMPLETED("completed");

    private String statusInLowerCase;

    Status(String statusInLowerCase) {
        this.statusInLowerCase = statusInLowerCase;
    }

    public String getStatusInLowerCase() {
        return statusInLowerCase;
    }
}
