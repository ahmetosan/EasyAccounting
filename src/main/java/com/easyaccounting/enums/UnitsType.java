package com.easyaccounting.enums;

public enum UnitsType {
    //IF MORE ENUMS NEEDED WILL BE UPDATED ACCORDINGLY, THIS ARE I CAN ONLY SEE FROM THE DOCUMENTS PICSs
    LB("Pound"),
    PC("Piece"),
    M("Meter"),
    KG("Kilogram"),
    FT("Feet"),
    IN("Inch"),
    GAL("Gallon"),
    L("Liter");
    private final String value;

    UnitsType(String value) {this.value = value;}

    public String getValue() {return value;}
}

