package com.easyaccounting.enums;

public enum MonthName {

    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");


    private final String value;

    MonthName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
