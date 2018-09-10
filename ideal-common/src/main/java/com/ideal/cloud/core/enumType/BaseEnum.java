package com.ideal.cloud.core.enumType;


import com.ideal.cloud.core.utils.StringHelper;

import java.util.regex.Pattern;

public class BaseEnum {
	public static enum YesOrNo {
		YES("是"), NO("否"),;
		
        private String leab;
        YesOrNo(String leab) {
            this.leab = leab;
        }
        public String getLeab() {
            return leab;
        }
        public static YesOrNo getYesOrNoByValue(Integer value) {
            if (null == value)
                return null;
            for (YesOrNo yn : YesOrNo.values()) {
                if (yn.ordinal() == value)
                    return yn;
            }
            return null;
        }
        public static YesOrNo getYesOrNoByName(String name) {
            if (StringHelper.isEmpty(name))
                return null;
            for (YesOrNo yn : YesOrNo.values()) {
                if (yn.name().equalsIgnoreCase(name))
                    return yn;
            }
            return null;
        }
    }

    public static enum NoOrYes {
    		NO("否"), YES("是"),;
        
        private String leab;
        private NoOrYes(String leab) {
            this.leab = leab;
        }
        public String getLeab() {
            return leab;
        }
        public static NoOrYes getNoOrYesByValue(Integer value) {
            if (null == value)
                return null;
            for (NoOrYes yn : NoOrYes.values()) {
                if (yn.ordinal() == value)
                    return yn;
            }
            return null;
        }
        public static NoOrYes getNoOrYesByName(String name) {
            if (StringHelper.isEmpty(name))
                return null;
            for (NoOrYes yn : NoOrYes.values()) {
                if (yn.name().equalsIgnoreCase(name))
                    return yn;
            }
            return null;
        }
    }
    
    public static enum SepartorType {
        EMPTY(""),EN_COMMA(","), CH_COMMA("，"), EN_HYPHEN("-"), CH_DASH("－"), EN_UNDERLINE("_"), POINT("."),SP_POINT("\\."),
        EQUAL("=")
        ;
        private String leab;
        private SepartorType(String leab) {
            this.leab = leab;
        }
        public static SepartorType getSepartorTypeByValue(Integer value) {
            if (null == value)
                return null;
            for (SepartorType type : SepartorType.values()) {
                if (type.ordinal() == value)
                    return type;
            }
            return null;
        }
        public String getLeab() {
            return leab;
        }
    }
    public static enum CharacterType {
        UTF8("utf-8"), GBK("gbk"), GB2312("gb2312"), ISO88591("ISO-8859-1"),;
        private String leab;
        private CharacterType(String leab) {
            this.leab = leab;
        }
        public static CharacterType getCharacterTypeByValue(Integer value) {
            if (null == value)
                return null;
            for (CharacterType type : CharacterType.values()) {
                if (type.ordinal() == value)
                    return type;
            }
            return null;
        }
        public String getLeab() {
            return leab;
        }
    }
    public static enum Sex {
        unknow("未知"), man("先生"), woman("女士"),;
        private String leab;
        private Sex(String leab) {
            this.leab = leab;
        }
        public String getLeab() {
            return leab;
        }
    }
    public enum RequestType {
        get("get"), post("post"),;
        private String leab;
        private RequestType(String leab) {
            this.leab = leab;
        }
        public String getLeab() {
            return leab;
        }
    }
    public static enum CompareType {
        eq1("等于", "="), eq2("等于", "=="), ne("不等于", "!="), gt("大于", ">"), lt("小于", "<"), gte("大于等于", ">="), lte("小于等于",
                "<="),;
        private String leab;
        private String value;
        private CompareType(String leab, String value) {
            this.leab = leab;
            this.value = value;
        }
        public String getLeab() {
            return leab;
        }
        public String getValue() {
            return value;
        }
    }
    
    public static enum OrderType {
        asc("正序"), desc("倒序"),;
        private String leab;
        
        private OrderType(String leab) {
            this.leab = leab;
        }
        
        public String getLeab() {
            return leab;
        }
        public static OrderType getOrderTypeByName(String value) {
            if(null == value)
                return null;
            for(OrderType type : OrderType.values()){
                if(type.name().equalsIgnoreCase(value))
                    return type;
            }
            return null;
        }
    }
    
    public static enum DateFormateUnit {
        YYYY("(?!0000)[0-9]{4}"),
        YYYYMM("^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])))$"),
        YYYYMMDD("^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])([-/.]?)(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])([-/.]?)(?:29|30)|(?:0?[13578]|1[02])([-/.]?)31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2([-/.]?)29)$"),
        YYYYMMDDHHMMSS("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")
        ;
        private String regular;
        
        private DateFormateUnit(String regular) {
            this.regular = regular;
        }
        
        public String getRegular(){
            return this.regular;
        }
        
        public boolean matches(String string){
            Pattern pattern = Pattern.compile(this.regular);
            return pattern.matcher(string).matches();
        }
    }
    
    public static enum DigitalFormateUnit{
        /**整数*/
        Integer("^(0|[-+]?[1-9][0-9]*)$"),
        /**非负整数*/
        Non_negative_integer("^(0|\\+?[1-9][0-9]*)$"),
        /**非正整数*/
        Non_positive_integer("^(0|\\-?[1-9][0-9]*)$"),
        /**非0正整数*/
        Non_zero_positive_integer("^\\+?[1-9][0-9]*$"),
        /**非0负整数*/
        Non_zero_negative_integer("^\\-?[1-9][0-9]*$"),
        
        
        /**浮点数*/
        Floating("^(-?\\d+)(\\.\\d+)?"),
        /**非负浮点数*/
        Non_negative_floating("^(0|[1-9][0-9]*)(\\.\\d+)?$"),
        /**正浮点数*/
        Positive_floating("^(([1-9][0-9]*)|(((0)|([1-9][0-9]))\\.[0-9]*[1-9][0-9]*)|([1-9][0-9]*\\.[0-9]+))$"),
        /**非正浮点数*/
        Non_positive_floating("^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"),
        /**负浮点数*/
        Negative_floating("^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"),
        
        /**数字*/
        Number("^[0-9]*$"),
        /**实数*/
        Real_number("^[-+]?\\d+(\\.\\d+)?$")
        ;
        private String regular;
        
        private DigitalFormateUnit(String regular) {
            this.regular = regular;
        }
        
        public String getRegular(){
            return this.regular;
        }
        
        public boolean matches(String string){
            Pattern pattern = Pattern.compile(this.regular);
            return pattern.matcher(string).matches();
        }
    }
    
    public static enum PhoneFormateUnit{
        CHINSE_MOBILE_PHONE("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$"),
        CHINSE_LANDLINE_PHONE("^\\(?(\\d{3,4})\\)?\\-?\\d{7,8}$")
        ;
        private String regular;
        
        private PhoneFormateUnit(String regular) {
            this.regular = regular;
        }
        
        public String getRegular(){
            return this.regular;
        }
        
        public boolean matches(String string){
            Pattern pattern = Pattern.compile(this.regular);
            return pattern.matcher(string).matches();
        }
    }
    
    public static enum StringFormateUnit{
        CHINESS_ENGLISH_NUMBER("^[\u4e00-\u9fa5_a-zA-Z0-9]+$"),
        CHINESS_ENGLISH("^[\u4e00-\u9fa5_a-zA-Z]+$"),
        CHINESS("^[\u4e00-\u9fa5]+$*"),
        ENGLISH_NUMBER("^[a-zA-Z0-9]+$"),
        ENGLISH("^[a-zA-Z]+$"),
        FIRST_ENGLISH_AND_NUMBER("^[a-zA-Z](1)[0-9]+$"),
        NUMBER_UP("^\\d(?:(?<=0)1|(?<=1)2|(?<=2)3|(?<=3)4|(?<=4)5|(?<=5)6|(?<=6)7|(?<=7)8|(?<=8)9){5,}$"),
        NUMBER_DOWN("^\\d(?:(?>=9)8|(?>=8)7|(?>=7)6|(?>=6)5|(?>=5)4|(?>=4)3|(?>=3)2|(?>=2)1|(?>=1)0){5,}$"),
        SAME_NUMBER("^(?:([0-9])\\1{5,})+$"),
        ;
        private String regular;
        
        private StringFormateUnit(String regular) {
            this.regular = regular;
        }
        
        public String getRegular(){
            return this.regular;
        }
        
        public boolean matches(String string){
            Pattern pattern = Pattern.compile(this.regular);
            return pattern.matcher(string).matches();
        }
    }
}
