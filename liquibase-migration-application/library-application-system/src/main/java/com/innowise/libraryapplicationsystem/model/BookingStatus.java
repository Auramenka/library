package com.innowise.libraryapplicationsystem.model;

public enum BookingStatus {

    ACTIVE("active"),
    CANCELED("canceled"),
    COMPLETED("completed");

    private String statusInLowerCase;

    BookingStatus(String statusInLowerCase) {
        this.statusInLowerCase = statusInLowerCase;
    }

    public String getStatusInLowerCase() {
        return statusInLowerCase;
    }
}
