package com.easyaccounting.enums;

public enum CompanyType {
    // just a placeholder, actual types will be placed
    IT("It"), TRAVEL("Travel"), FINTECH("Fintech");

    private final String value;

    CompanyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
