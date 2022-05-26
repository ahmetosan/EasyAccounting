package com.easyaccounting.enums;

public enum InvoiceType {

    SALESINVOICE("S_Invoice"), PURCHASEINVOICE("P_Invoice");

    private final String value;

    InvoiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
