package com.south.digital_conversion;

import java.util.regex.Pattern;

public class DigitalConversion {

    public static void main(String[] args) {
//        String number2Int = chineseNumber2Int("啊的十四已三");
//        System.out.println(number2Int + "        ======");

        String s = toChinese1("15");
        System.out.println(s);
    }

    /**
     * 中文数字转阿拉伯数字
     *
     * @param chineseNumber
     * @return
     */
    private static String chineseNumber2Int(String chineseNumber) {
        StringBuilder strResult = new StringBuilder("");
        Integer result = null;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            boolean digital = true;
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if (result == null) {
                        result = 0;
                    }
                    digital = false;
                    if (0 != count) {//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) {//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        if (result == null) {
                            result = 0;
                        }
                        digital = false;
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
                strResult.append(result);
                if (digital) {
                    strResult.append(c);
                }
                return strResult.toString();
            }
            if (digital) {
                if (result != null) {
                    result += temp;
                    strResult.append(result);
                    result = null;
                    temp = 0;
                }
                strResult.append(c);
            }

        }
        return strResult.toString();
    }

    private static String toChinese(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        String temp = "";
        for (int i = 0; i < n; i++) {
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            boolean matches = pattern.matcher(String.valueOf(str.charAt(i))).matches();
            if (matches){
                temp += str.charAt(i);
                continue;
            }else {
                int l = temp.length();
                for (int j = 0; j < l; j++) {
                    int num = temp.charAt(j) - '0';
                    if (j != l - 1 && num != 0) {
                        result += s1[num] + s2[l - 2 - j];
                    } else {
                        result += s1[num];
                    }
                }
                temp = "";
                result +=str.charAt(i);
            }
        }
        return result;
    }


    /**
     * 中文數字转阿拉伯数组【十万九千零六十  --> 109060】
     *
     * @param chineseNumber
     * @return
     * @author 雪见烟寒
     */
    private static int chineseNumber2Int1(String chineseNumber) {
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一', '二', '三', '四', '五', '六', '七', '八', '九'};
        char[] chArr = new char[]{'十', '百', '千', '万', '亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if (0 != count) {//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if (b) {//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

    private static String toChinese1(String str) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
    }
}
