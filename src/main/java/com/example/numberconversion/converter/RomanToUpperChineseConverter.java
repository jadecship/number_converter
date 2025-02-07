package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class RomanToUpperChineseConverter extends AbstractNumberConverter {
    private static final Map<Character, Integer> ROMAN_MAP = new HashMap<>();
    private static final String[] UPPER_CHINESE_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] UNITS = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};

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
        String upperChinese = convertArabicToUpperChinese(arabicNumber);
        return new NumberWrapper(upperChinese, NumberType.UPPER_CHINESE);
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

    private String convertArabicToUpperChinese(int number) {
        if (number == 0) {
            return UPPER_CHINESE_NUMBERS[0];
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
                    result.append(UPPER_CHINESE_NUMBERS[0]);
                    zeroFlag = false;
                }
                result.append(UPPER_CHINESE_NUMBERS[digit]);
                result.append(UNITS[unitIndex]);
            }
        }

        // 处理末尾的零
        while (result.length() > 0 && result.charAt(result.length() - 1) == '零') {
            result.deleteCharAt(result.length() - 1);
        }

        // 处理壹拾的特殊情况
        if (result.toString().startsWith("壹拾")) {
            result.delete(0, 1);
        }

        return result.toString();
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return sourceType == NumberType.ROMAN && targetType == NumberType.UPPER_CHINESE;
    }
}