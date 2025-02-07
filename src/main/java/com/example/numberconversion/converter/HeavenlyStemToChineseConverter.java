package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class HeavenlyStemToChineseConverter extends AbstractNumberConverter {
    private static final Map<Character, String> HEAVENLY_STEM_MAP = new HashMap<>();

    static {
        HEAVENLY_STEM_MAP.put('甲', "一");
        HEAVENLY_STEM_MAP.put('乙', "二");
        HEAVENLY_STEM_MAP.put('丙', "三");
        HEAVENLY_STEM_MAP.put('丁', "四");
        HEAVENLY_STEM_MAP.put('戊', "五");
        HEAVENLY_STEM_MAP.put('己', "六");
        HEAVENLY_STEM_MAP.put('庚', "七");
        HEAVENLY_STEM_MAP.put('辛', "八");
        HEAVENLY_STEM_MAP.put('壬', "九");
        HEAVENLY_STEM_MAP.put('癸', "十");
    }

    @Override
    protected NumberWrapper convertInternal(NumberWrapper input) {
        String heavenlyStem = (String) input.getValue();
        StringBuilder result = new StringBuilder();
        for (char c : heavenlyStem.toCharArray()) {
            result.append(HEAVENLY_STEM_MAP.get(c));
        }
        return new NumberWrapper(result.toString(), NumberType.LOWER_CHINESE);
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return sourceType == NumberType.HEAVENLY_STEM && targetType == NumberType.LOWER_CHINESE;
    }
}