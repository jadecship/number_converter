package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

public abstract class AbstractNumberConverter {
    protected abstract NumberWrapper convertInternal(NumberWrapper input);

    public NumberWrapper convert(NumberWrapper input) {
        return convertInternal(input);
    }

    public abstract boolean canConvert(NumberType sourceType, NumberType targetType);
}