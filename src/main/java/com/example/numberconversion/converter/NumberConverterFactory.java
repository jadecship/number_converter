package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.ArrayList;
import java.util.List;

public class NumberConverterFactory {
    private static final List<AbstractNumberConverter> converters = new ArrayList<>();

    static {
        converters.add(new ArabicToChineseConverter());
        converters.add(new ChineseToArabicConverter());
        converters.add(new RomanToArabicConverter());
        converters.add(new HeavenlyStemToChineseConverter());
        converters.add(new RomanToLowerChineseConverter());
        converters.add(new RomanToUpperChineseConverter());
    }

    public static NumberWrapper convert(NumberWrapper input, NumberType targetType) {
        for (AbstractNumberConverter converter : converters) {
            if (converter.canConvert(input.getType(), targetType)) {
                return converter.convert(input);
            }
        }
        throw new IllegalArgumentException("不支持从 " + input.getType() + " 到 " + targetType + " 的转换");
    }
}