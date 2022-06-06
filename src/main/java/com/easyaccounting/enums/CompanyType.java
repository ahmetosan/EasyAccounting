package com.easyaccounting.enums;

public enum CompanyType {

    Vendor("vendor"), CLIENT("Client");

    private final String value;

    CompanyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
