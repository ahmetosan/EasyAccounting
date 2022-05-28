package com.easyaccounting.enums;

public enum ProductStatus {

    ACTIVE("Active"), PASSIVE("Passive");

    private String value;

    ProductStatus(String value){this.value=value;}

    public String getValue(){return value;}
}
