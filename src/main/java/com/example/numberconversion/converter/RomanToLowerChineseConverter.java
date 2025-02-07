package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class RomanToLowerChineseConverter extends AbstractNumberConverter {
    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();
    private static final String[] LOWER_CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十", "百", "千", "亿"};

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
        int arabicNumber = convertRomanToArabic(romanNumber);
        String lowerChinese = convertArabicToLowerChinese(arabicNumber);
        return new NumberWrapper(lowerChinese, NumberType.LOWER_CHINESE);
    }

    private int convertRomanToArabic(String romanNumber) {
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
        return result;
    }

    private String convertArabicToLowerChinese(int number) {
        if (number == 0) {
            return LOWER_CHINESE_NUMBERS[0];
        }
        StringBuilder result = new StringBuilder();
        String numStr = String.valueOf(number);
        int length = numStr.length();
        boolean zeroFlag = false;

        for (int i = 0; i < length; i++) {
            int digit = numStr.charAt(i) - '0';
            int unitIndex = length - i - 1;

            if (digit == 0) {
                zeroFlag = true;
            } else {
                if (zeroFlag) {
                    result.append(LOWER_CHINESE_NUMBERS[0]);
                    zeroFlag = false;
                }
                result.append(LOWER_CHINESE_NUMBERS[digit]);
                result.append(UNITS[unitIndex]);
            }
        }

        // 处理末尾的零
        while (result.length() > 0 && result.charAt(result.length() - 1) == '零') {
            result.deleteCharAt(result.length() - 1);
        }

        // 处理一十的特殊情况
        if (result.toString().startsWith("一十")) {
            result.delete(0, 1);
        }

        return result.toString();
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return sourceType == NumberType.ROMAN && targetType == NumberType.LOWER_CHINESE;
    }
}