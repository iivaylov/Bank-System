package com.mybanksystem.models.enums;

public enum AccountType {
    CHECKING,
    SAVINGS,
    CREDIT;

    @Override
    public String toString() {
        switch (this) {
            case CHECKING:
                return "Checking";
            case SAVINGS:
                return "Savings";
            case CREDIT:
                return "Credit";
            default:
                throw new IllegalArgumentException();
        }
    }
}
