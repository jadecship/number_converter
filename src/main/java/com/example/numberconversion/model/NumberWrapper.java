package com.example.numberconversion.model;

public class NumberWrapper {
    private final Object value;
    private final NumberType type;

    public NumberWrapper(Object value, NumberType type) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public NumberType getType() {
        return type;
    }
}