package com.mty.cisp.asnyc;

public enum EventType {
    comment(1);

    private int value;
    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
