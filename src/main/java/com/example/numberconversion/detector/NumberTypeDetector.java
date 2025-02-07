package com.example.numberconversion.detector;

import com.example.numberconversion.model.NumberType;
import com.example.numberconversion.model.NumberWrapper;

import java.util.regex.Pattern;

public class NumberTypeDetector {
    private static final Pattern ARABIC_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern LOWER_CHINESE_PATTERN = Pattern.compile("^[零一二三四五六七八九十百千万亿]+$");
    private static final Pattern UPPER_CHINESE_PATTERN = Pattern.compile("^[零壹贰叁肆伍陆柒捌玖拾佰仟万亿]+$");
    private static final Pattern ROMAN_PATTERN = Pattern.compile("^[IVXLCDM]+$");
    private static final Pattern HEAVENLY_STEM_PATTERN = Pattern.compile("^[甲乙丙丁戊己庚辛壬癸]+$");

    public static NumberWrapper detect(Object input) {
        if (input instanceof Integer) {
            return new NumberWrapper(input, NumberType.ARABIC);
        } else if (input instanceof String) {
            String str = (String) input;
            if (ARABIC_PATTERN.matcher(str).matches()) {
                return new NumberWrapper(Integer.parseInt(str), NumberType.ARABIC);
            } else if (LOWER_CHINESE_PATTERN.matcher(str).matches()) {
                return new NumberWrapper(str, NumberType.LOWER_CHINESE);
            } else if (UPPER_CHINESE_PATTERN.matcher(str).matches()) {
                return new NumberWrapper(str, NumberType.UPPER_CHINESE);
            } else if (ROMAN_PATTERN.matcher(str).matches()) {
                return new NumberWrapper(str, NumberType.ROMAN);
            } else if (HEAVENLY_STEM_PATTERN.matcher(str).matches()) {
                return new NumberWrapper(str, NumberType.HEAVENLY_STEM);
            }
        }
        throw new IllegalArgumentException("无法识别的数字类型: " + input);
    }
}