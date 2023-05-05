package com.mybanksystem.models.enums;

public enum Currency {
    USD,
    EUR,
    GBP,
    BGN,
    CZK,
    RON,
    RUB;

    @Override
    public String toString() {
        switch (this) {
            case USD:
                return "USD";
            case EUR:
                return "EUR";
            case GBP:
                return "GBP";
            case BGN:
                return "BGN";
            case CZK:
                return "CZK";
            case RON:
                return "RON";
            case RUB:
                return "RUB";
            default:
                throw new IllegalArgumentException();
        }
    }
}
