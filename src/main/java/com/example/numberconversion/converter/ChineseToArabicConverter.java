package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class ChineseToArabicConverter extends AbstractNumberConverter {
    private static final Map<Character, Integer> LOWER_CHINESE_MAP = new HashMap<>();
    private static final Map<Character, Integer> UPPER_CHINESE_MAP = new HashMap<>();
    private static final Map<Character, Integer> UNITS_MAP = new HashMap<>();

    static {
        String[] LOWER_CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] UPPER_CHINESE_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] UNITS = {"", "十", "百", "千", "万", "亿"};

        for (int i = 0; i < LOWER_CHINESE_NUMBERS.length; i++) {
            LOWER_CHINESE_MAP.put(LOWER_CHINESE_NUMBERS[i].charAt(0), i);
        }
        for (int i = 0; i < UPPER_CHINESE_NUMBERS.length; i++) {
            UPPER_CHINESE_MAP.put(UPPER_CHINESE_NUMBERS[i].charAt(0), i);
        }
        UNITS_MAP.put('十', 10);
        UNITS_MAP.put('百', 100);
        UNITS_MAP.put('千', 1000);
        UNITS_MAP.put('万', 10000);
        UNITS_MAP.put('亿', 100000000);
    }

    @Override
    protected NumberWrapper convertInternal(NumberWrapper input) {
        String chineseNumber = (String) input.getValue();
        NumberType sourceType = input.getType();
        Map<Character, Integer> chineseMap = sourceType == NumberType.LOWER_CHINESE ? LOWER_CHINESE_MAP : UPPER_CHINESE_MAP;
        int result = 0;
        int temp = 0;
        for (int i = 0; i < chineseNumber.length(); i++) {
            char c = chineseNumber.charAt(i);
            if (chineseMap.containsKey(c)) {
                temp = chineseMap.get(c);
            } else if (UNITS_MAP.containsKey(c)) {
                int unit = UNITS_MAP.get(c);
                if (unit < 10000) {
                    if (temp == 0) {
                        temp = 1;
                    }
                    result += temp * unit;
                    temp = 0;
                } else {
                    if (temp == 0) {
                        temp = 1;
                    }
                    result = (result + temp) * unit;
                    temp = 0;
                }
            }
        }
        result += temp;
        return new NumberWrapper(result, NumberType.ARABIC);
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return (sourceType == NumberType.LOWER_CHINESE || sourceType == NumberType.UPPER_CHINESE) && targetType == NumberType.ARABIC;
    }
}