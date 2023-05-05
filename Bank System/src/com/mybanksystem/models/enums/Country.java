package com.mybanksystem.models.enums;

public enum Country {
    UNITEDSTATES,
    GERMANY,
    UNITEDKINGDOM,
    BULGARIA,
    CZECHREPUBLIC,
    ROMANIA,
    RUSSIA;

    @Override
    public String toString() {
        switch (this) {
            case UNITEDSTATES:
                return "United States";
            case GERMANY:
                return "Germany";
            case UNITEDKINGDOM:
                return "United Kingdom";
            case BULGARIA:
                return "Bulgaria";
            case CZECHREPUBLIC:
                return "Czech Republic";
            case ROMANIA:
                return "Romania";
            case RUSSIA:
                return "Russia";
            default:
                throw new IllegalArgumentException();
        }
    }
}
