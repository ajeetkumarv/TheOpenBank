package com.theopenbank.customer.model;

public enum Gender {
    UNKNOWN(0),
    MALE(1),
    FEMALE(2),
    OTHER(3);

    private final int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Gender fromCode(int code) {
        for (Gender g : Gender.values()) {
            if (g.code == code) return g;
        }
        return UNKNOWN;
    }
}

