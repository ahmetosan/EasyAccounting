package com.easyaccounting.enums;

public enum State {

    STATECODE("state_code"), STATENAME("state_name");

    private final String value;

    State(String value) {this.value = value;}

    public String getValue() {return value;}

}

