package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabicConverter extends AbstractNumberConverter {
    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();

    static {
        ROMAN_MAP.put('I', 1);
        ROMAN_MAP.put('V', 5);
        ROMAN_MAP.put('X', 10);
        ROMAN_MAP.put('L', 50);
        ROMAN_MAP.put('C', 100);
        ROMAN_MAP.put('D', 500);
        ROMAN_MAP.put('M', 1000);
    }

    @Override
    protected NumberWrapper convertInternal(NumberWrapper input) {
        String romanNumber = (String) input.getValue();
        int result = 0;
        int prevValue = 0;
        for (int i = romanNumber.length() - 1; i >= 0; i--) {
            char c = romanNumber.charAt(i);
            int value = ROMAN_MAP.get(c);
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return new NumberWrapper(result, NumberType.ARABIC);
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return sourceType == NumberType.ROMAN && targetType == NumberType.ARABIC;
    }
}