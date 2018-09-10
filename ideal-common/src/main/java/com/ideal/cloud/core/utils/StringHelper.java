package com.ideal.cloud.core.utils;

import com.ideal.cloud.core.enumType.BaseEnum;
import com.ideal.cloud.core.enumType.BaseEnum.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    private static final String POSSIBLE_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String POSSIBLE_NUMBER = "0123456789";
    private static final String[] TEL_PREX = { "13", "15", "18" };
    
    public static boolean isEmpty(Object param) {
        if (null == param || "".equals(param.toString().trim()) || "null".equalsIgnoreCase(param.toString().trim()))
            return true;
        return false;
    }
    public static String valueOf(Object object) {
        if (isEmpty(object))
            return null;
        String returnStr = object.toString();
        if (isEmpty(returnStr))
            return null;
        return returnStr.trim();
    }
    public static String valueOf(BigDecimal value) {
        if (isEmpty(value))
            return null;
        String returnStr = value.toString();
        if (isEmpty(returnStr))
            return null;
        if (returnStr.indexOf(".") > 0) {
            // 去掉多余的0
            returnStr = returnStr.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            returnStr = returnStr.replaceAll("[.]$", "");
        }
        return returnStr.trim();
    }
    public static String stringValueOf(Object object) {
        String returnStr = valueOf(object);
        if (isEmpty(returnStr))
            return "";
        return returnStr.trim();
    }
    public static String stringValueOf(BigDecimal value) {
        String returnStr = String.valueOf(value);
        return formateNum(returnStr);
    }
    public static String formateNum(String string) {
        if (isEmpty(string))
            return "";
        if (string.indexOf(".") > 0) {
            // 去掉多余的0
            string = string.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            string = string.replaceAll("[.]$", "");
        }
        return string.trim();
    }
    public static Integer parseInteger(String string) {
        if (isEmpty(string))
            return null;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return Integer.parseInt(formateNum(string));
        }
        return null;
    }
    public static Long parseLong(String string) {
        if (isEmpty(string))
            return null;
        if (BaseEnum.DigitalFormateUnit.Real_number.matches(string)) {
            return Long.parseLong(formateNum(string));
        }
        return null;
    }
    public static Double parseDouble(String string) {
        if (isEmpty(string))
            return null;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return Double.parseDouble(formateNum(string));
        }
        return null;
    }
    public static BigDecimal parseBigDecimal(String string) {
        if (isEmpty(string))
            return null;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return new BigDecimal(formateNum(string));
        }
        return null;
    }
    public static BigDecimal parseBigDecimal(String string, BigDecimal defaultValue) {
        if (isEmpty(string))
            return defaultValue;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return new BigDecimal(formateNum(string));
        }
        return defaultValue;
    }
    public static Integer parseInteger(String string, Integer defaultValue) {
        if (isEmpty(string))
            return defaultValue;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return Integer.parseInt(formateNum(string));
        }
        return defaultValue;
    }
    public static Long parseLong(String string, Long defaultValue) {
        if (isEmpty(string))
            return defaultValue;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return Long.parseLong(formateNum(string));
        }
        return defaultValue;
    }
    public static Boolean parseBoolean(String string) {
        if (isEmpty(string))
            return null;
        return Boolean.parseBoolean(string);
    }
    public static Float parseFloat(String string) {
        if (isEmpty(string))
            return null;
        if (DigitalFormateUnit.Real_number.matches(string)) {
            return Float.parseFloat(formateNum(string));
        }
        return null;
    }
    public static String concatToString(Object... arrays) {
        StringBuffer sb = new StringBuffer();
        for (Object obj : arrays) {
            if (isEmpty(obj))
                return "";
            else
                sb.append(obj);
        }
        return sb.toString();
    }
    public static String concatToString2(Object... arrays) {
        StringBuffer sb = new StringBuffer();
        for (Object obj : arrays) {
            if (isEmpty(obj))
                continue;
            else
                sb.append(obj);
        }
        return sb.toString();
    }
    public static StringBuilder concatToString(StringBuilder sb1, StringBuilder sb2) {
        StringBuilder sb = new StringBuilder();
        sb.append(sb2);
        sb.append(sb1);
        return sb;
    }
    public static String concatToString(SepartorType separtorType, String... strings) {
        StringBuffer sb = new StringBuffer();
        for (String string : strings) {
            if (isEmpty(string))
                continue;
            else {
                sb.append(string);
                sb.append(separtorType.getLeab());
            }
        }
        return getSubString(sb.toString());
    }
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomNumber(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    // 前面补零
    public static String addZero(int code, int length) {
        if (isEmpty(code))
            return null;
        String regex = "%0" + length + "d";
        String sb = String.format(regex, code);
        return sb;
    }
    public static int compareVersion(String version1, String version2) throws Exception {
        if (isEmpty(version1) || isEmpty(version2))
            return 0;
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
    public static boolean equal(Object o1, Object o2) {
        if (isEmpty(o1) && isEmpty(o2))
            return true;
        else if (isEmpty(o1) || isEmpty(o2))
            return false;
        else if (valueOf(o1).equals(valueOf(o2)))
            return true;
        else
            return false;
    }
    public static boolean equalExceptNull(Object o1, Object o2) {
        if (isEmpty(o1) || isEmpty(o2))
            return false;
        else if (valueOf(o1).equals(valueOf(o2)))
            return true;
        else
            return false;
    }
    public static Object compareNum(Object o1, Object o2) throws Exception {
        if (isEmpty(o1) && isEmpty(o2))
            return null;
        else if (isEmpty(o1))
            return o2;
        else if (isEmpty(o2))
            return o1;
        else {
            String s1 = valueOf(o1);
            String s2 = valueOf(o2);
            int ret = compareVersion(s1, s2);
            if (ret > 0)
                return o1;
            else
                return o2;
        }
    }
    public static String parseString(Object obj) {
        return parseString(obj, "");
    }
    public static String parseString(Object obj, String str) {
        boolean flag = false;
        if (obj == null) {
            flag = true;
        }
        if (flag == false) {
            if ("null".equalsIgnoreCase(obj.toString()) || "".equalsIgnoreCase(obj.toString())) {
                flag = true;
            }
        }
        if (flag) {
            return str;
        } else {
            return obj.toString().trim();
        }
    }
    public static String filterEnter(String value) {
        value = parseString(value);
        value = value.replaceAll("\r", "");
        value = value.replaceAll("\n", "<br/>");
        return value;
    }
    public static boolean checkDate(String date) {
        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(date);
        boolean b = m.matches();
        return b;
    }
    public static String filterSQL(String sql) {
        Pattern pattern1 = Pattern.compile("[\\s]*(.+)[\\s]*=[\\s]*\\1[\\s]+((and)|(or))[\\s]+",
                Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[\\s]*((and)|(or))[\\s]+(.+)[\\s]*=[\\s]*\\4[\\)]",
                Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("[\\s]*((and)|(or))[\\s]+(.+)[\\s]*=[\\s]*\\4[\\s]+",
                Pattern.CASE_INSENSITIVE);
        Pattern pattern4 = Pattern.compile("[\\s]*where[\\s]+(.*)[\\s]*=[\\s]*\\1[\\)]", Pattern.CASE_INSENSITIVE);
        Pattern pattern5 = Pattern.compile("[\\s]*where[\\s]+(.*)[\\s]*=[\\s]*\\1[\\s]+", Pattern.CASE_INSENSITIVE);
        String str = sql;
        str = " " + str;
        str = str + " ";
        Matcher matcher = pattern1.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll(" ");
        }
        matcher = pattern2.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll(" ) ");
        }
        matcher = pattern3.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll(" ");
        }
        matcher = pattern4.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll(" ) ");
        }
        matcher = pattern5.matcher(str);
        while (matcher.find()) {
            str = matcher.replaceAll(" ");
        }
        return str.trim();
    }
    public static boolean checkFloat(String str) {
        boolean b = str.matches("^(([0-9]+\\.[0-9]+)|([0-9]*))$");
        return b;
    }
    public static boolean checkNumber(String str) {
        boolean b = str.matches("[\\d]+");
        return b;
    }
    
    public static boolean checkString(String s1,String s2) {
        if(isEmpty(s1) || isEmpty(s2))
            return false;
        return s1.contains(s2);
    }
    public static String getRandomNum() {
        String t = String.valueOf(System.currentTimeMillis());
        t = t.substring(t.length() - 5, t.length());
        String rad = "0123456789";
        StringBuffer result = new StringBuffer();
        Random rand = new Random();
        int length = 27;
        for (int i = 0; i < length; i++) {
            int randNum = rand.nextInt(10);
            result.append(rad.substring(randNum, randNum + 1));
        }
        return t + result;
    }
    public static String getRandomNumByLength(int length) {
        String rad = "0123456789";
        Random rand = new Random();
        StringBuffer result = new StringBuffer();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                int randNum = rand.nextInt(10);
                result.append(rad.substring(randNum, randNum + 1));
            }
        }
        return result.toString();
    }
    public static Object viewZero(Object o) {
        if (Double.parseDouble(String.valueOf(o)) == 0) {
            return "0";
        } else {
            return o;
        }
    }
    public static String subStrByLength(String str, int length) {
        if (null == str)
            return "";
        else if (str.length() > length)
            return str.substring(0, length);
        else
            return str;
    }
    public static String bytesToHexString(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder("");
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
            return null;
        }
        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            int j = paramArrayOfByte[i] & 0xFF;
            String str = Integer.toHexString(j);
            if (str.length() < 2) {
                localStringBuilder.append(0);
            }
            localStringBuilder.append(str);
        }
        return localStringBuilder.toString();
    }
    public static String generateRandomNum(int paramInt) {
        char[] arrayOfChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        StringBuffer localStringBuffer = new StringBuffer();
        Random localRandom = new Random();
        for (int i = 0; i < paramInt; ++i) {
            localStringBuffer.append(arrayOfChar[localRandom.nextInt(arrayOfChar.length)]);
        }
        return localStringBuffer.toString();
    }
    public static String fillLeft(String paramString, char paramChar, int paramInt) {
        return fillStr(paramString, paramChar, paramInt, true);
    }
    public static String fillRight(String paramString, char paramChar, int paramInt) {
        return fillStr(paramString, paramChar, paramInt, false);
    }
    private static String fillStr(String paramString, char paramChar, int paramInt, boolean paramBoolean) {
        int i = paramInt - paramString.length();
        if (i <= 0)
            return paramString;
        StringBuilder localStringBuilder = new StringBuilder(paramString);
        for (; i > 0; --i) {
            if (paramBoolean)
                localStringBuilder.insert(0, paramChar);
            else
                localStringBuilder.append(paramChar);
        }
        return localStringBuilder.toString();
    }
    public static String replaceBlank(String sourceStr) {
        String dest = "";
        if (sourceStr != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(sourceStr);
            dest = m.replaceAll("");
        }
        return dest;
    }
    public static String getArrayStr(Object[] objects) {
        StringBuffer sb = new StringBuffer("");
        if (isEmpty(objects))
            return sb.toString();
        for (Object object : objects) {
            if(!isEmpty(object)) {
                sb.append(stringValueOf(object));
                sb.append(SepartorType.EN_COMMA.getLeab());
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }
    
    public static String getRandom(int min, int max) {
        return valueOf(NumberHelper.getRandom(min, max));
    }
    
    public static String getSubString(String string) {
        if (isEmpty(string))
            return "";
        else
            return string.substring(0, string.length() - 1);
    }
    
    public static String replace(String str, String[] codes, String... values) {
        if (null == codes || codes.length < 1 || null == values || values.length < 1)
            return str;
        for (int i = 0; i < codes.length; i++) {
            if (!isEmpty(values[i]))
                str = str.replace(codes[i], values[i]);
        }
        return str;
    }
   
    public static String generateRandomString(int length, SepartorType code) {
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));
            if (null != code)
                if (i != 0 && (i + 1) % 4 == 0 && i + 1 < length)
                    sb.append(code.getLeab());
        }
        return sb.toString();
    }
    public static String[] splitToArray(String str, SepartorType code) {
        if (isEmpty(str))
            return null;
        return str.split(code.getLeab());
    }
    public static String generateRandomNumber(int length, SepartorType code) {
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(POSSIBLE_NUMBER.charAt(random.nextInt(POSSIBLE_NUMBER.length())));
            if (null != code)
                if (i != 0 && (i + 1) % 4 == 0 && i + 1 < length)
                    sb.append(code.getLeab());
        }
        return sb.toString();
    }
    public static String hideMobileNum(String mobile) {
        if (isEmpty(mobile))
            return null;
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
    public static boolean checkLenght(String str, int length) {
        if (isEmpty(str))
            return false;
        return str.length() >= length;
    }
    public static String getSubStringStr(String str, int beg, int end) {
        if (isEmpty(str) || str.length() < beg || str.length() < end - 1)
            return null;
        else if (str.length() < end)
            end = str.length();
        return str.substring(beg, end);
    }
    public static String getRandomTel() {
        int index = NumberHelper.getRandom(0, TEL_PREX.length - 1);
        String first = TEL_PREX[index];
        String second = String.valueOf(NumberHelper.getRandom(1, 9100) + 10000).substring(1);
        String thrid = String.valueOf(NumberHelper.getRandom(1, 99100) + 100000).substring(1);
        return first + second + thrid;
    }
    public static String changeCharset(String str, CharacterType oldCharset, CharacterType newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes(oldCharset.getLeab());
            return new String(bs, newCharset.getLeab());
        }
        return null;
    }
    public static String changeCharset(String str, CharacterType newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes();
            return new String(bs, newCharset.getLeab());
        }
        return null;
    }
    
    public static List<String[]> splitAry(String[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;
        
        List<List<String>> subAryList = new ArrayList<List<String>>();
        for (int i = 0; i < count; i++) {
            int index = i * subSize;
            List<String> list = new ArrayList<String>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            subAryList.add(list);
        }
        List<String[]> subAry = new ArrayList<String[]>();
        for (int i = 0; i < subAryList.size(); i++) {
            List<String> subList = subAryList.get(i);
            String[] subAryItem = new String[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                subAryItem[j] = subList.get(j);
            }
            subAry.add(subAryItem);
        }
        return subAry;
    }
    
    public static String orgNameHide(String orgName){
        if(isEmpty(orgName)){
            return null;
        }else{
            if(orgName.length() > 6){
                return orgName.substring(0, 1) + "***" +  orgName.substring(orgName.length()-5);
            }else{
                return orgName;
            }
        }
    }
    
    public static String orgNameHide(String orgName,int num){
        if(isEmpty(orgName)){
            return null;
        }else{
            if(orgName.length() > num+1){
                return orgName.substring(0, 1) + "***" +  orgName.substring(orgName.length()-num);
            }else{
                return orgName;
            }
        }
    }
    
    public final static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
