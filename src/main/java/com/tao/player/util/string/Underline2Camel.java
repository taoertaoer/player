package com.tao.player.util.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Underline2Camel {
    private static Pattern AZ_PATTERN = Pattern.compile("[A-Z]");
    private static Pattern UNDERLINE_PATTERN = Pattern.compile("_([a-z])");

    public static void main(String[] args) {
        //System.out.println("Hello world");
        //System.out.println(underline("ABC"));
        //System.out.println(camel("_a_b_c"));
        //System.out.println(underline("ABhongC"));
        System.out.println(camel("xw_a_b_c"));
    }

    /**
     * 将驼峰格式字符串转下划线格式
     * @param str string 待处理字符串
     * @return string 处理结果
     */
    public static String underline(String str) {
        //正则匹配大写字符转成小写并在前面加上下划线
        Matcher matcher = AZ_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //正则之前的字符和被替换的字符放到StringBuffer中
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            //把之后的字符串也添加到StringBuffer对象中
            matcher.appendTail(sb);
        } else {
            return sb.toString();
        }
        return underline(sb.toString());
    }

    /**
     * 将下划线格式字符串转驼峰格式
     * @param str string 待处理字符串
     * @return string 处理结果
     */
    public static String camel(String str) {
        //正则匹配下划线及后一个字符，删除下划线并将匹配的字符转成大写
        Matcher matcher = UNDERLINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //正则之前的字符和被替换的字符放到StringBuffer中
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的字符串也添加到StringBuffer对象中
            matcher.appendTail(sb);
        } else {
            return sb.toString();
        }
        return camel(sb.toString());
    }
}
