package com.mybanksystem.models.enums;

public enum City {
    WASHINGTONDC,
    FRANKFURT,
    LONDON,
    SOFIA,
    WARSAW,
    BUCHAREST,
    MOSCOW;

    @Override
    public String toString() {
        switch (this) {
            case WASHINGTONDC:
                return "Washington DC";
            case FRANKFURT:
                return "Frankfurt";
            case LONDON:
                return "London";
            case SOFIA:
                return "Sofia";
            case WARSAW:
                return "Warsaw";
            case BUCHAREST:
                return "Bucharest";
            case MOSCOW:
                return "Moscow";
            default:
                throw new IllegalArgumentException();
        }
    }
}
