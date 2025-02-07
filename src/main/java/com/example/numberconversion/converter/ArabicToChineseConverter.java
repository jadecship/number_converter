package com.example.numberconversion.converter;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.HashMap;
import java.util.Map;

public class ArabicToChineseConverter extends AbstractNumberConverter {
    private static final String[] LOWER_CHINESE_NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UPPER_CHINESE_NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final String[] UNITS = {"", "十", "百", "千", "万", "十", "百", "千", "亿"};

    @Override
    protected NumberWrapper convertInternal(NumberWrapper input) {
        int arabicNumber = (int) input.getValue();
        NumberType targetType = getTargetType(input.getType());
        String result;
        if (targetType == NumberType.LOWER_CHINESE) {
            result = convertToChinese(arabicNumber, LOWER_CHINESE_NUMBERS);
        } else {
            result = convertToChinese(arabicNumber, UPPER_CHINESE_NUMBERS);
        }
        return new NumberWrapper(result, targetType);
    }

    private String convertToChinese(int number, String[] chineseNumbers) {
        if (number == 0) {
            return chineseNumbers[0];
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
                    result.append(chineseNumbers[0]);
                    zeroFlag = false;
                }
                result.append(chineseNumbers[digit]);
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

    private NumberType getTargetType(NumberType sourceType) {
        if (sourceType == NumberType.ARABIC) {
            return NumberType.LOWER_CHINESE;
        }
        return null;
    }

    @Override
    public boolean canConvert(NumberType sourceType, NumberType targetType) {
        return sourceType == NumberType.ARABIC && (targetType == NumberType.LOWER_CHINESE || targetType == NumberType.UPPER_CHINESE);
    }
}