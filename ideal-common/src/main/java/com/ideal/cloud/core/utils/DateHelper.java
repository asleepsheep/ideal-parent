package com.ideal.cloud.core.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateHelper {
    public final static int millis_lenght = 13;
    /** 相等 */
    public final static int EQUALLY = 0;
    /** 大于 */
    public final static int GREATERTHAN = 1;
    /** 小于 */
    public final static int LESSTHAN = -1;
    public static enum TimeUnit {
        millisecond {
            public int getUnit() {
                return 1000;
            }
            int getCalendarField() {
                return Calendar.MILLISECOND;
            }
            long getMillisNum() {
                return 1;
            }
            String getChineseName() {
                return "毫秒";
            }
            int beginNum() {
                return 0;
            }
        },
        second {
            public int getUnit() {
                return 60;
            }
            int getCalendarField() {
                return Calendar.SECOND;
            }
            long getMillisNum() {
                return 1000;
            }
            String getChineseName() {
                return "秒";
            }
            int beginNum() {
                return 0;
            }
        },
        minute {
            public int getUnit() {
                return 60;
            }
            int getCalendarField() {
                return Calendar.MINUTE;
            }
            long getMillisNum() {
                return 1000 * 60;
            }
            String getChineseName() {
                return "分";
            }
            int beginNum() {
                return 0;
            }
        },
        hour {
            public int getUnit() {
                return 24;
            }
            int getCalendarField() {
                return Calendar.HOUR_OF_DAY;
            }
            long getMillisNum() {
                return 1000 * 60 * 60;
            }
            String getChineseName() {
                return "小时";
            }
            int beginNum() {
                return 0;
            }
        },
        day {
            public int getUnit() {
                return 30;
            }
            int getCalendarField() {
                return Calendar.DAY_OF_MONTH;
            }
            long getMillisNum() {
                return 1000 * 60 * 60 * 24;
            }
            String getChineseName() {
                return "天";
            }
            int beginNum() {
                return 1;
            }
        },
        month {
            public int getUnit() {
                return 12;
            }
            int getCalendarField() {
                return Calendar.MONTH;
            }
            long getMillisNum() {
                return 1000 * 60 * 60 * 24 * 30;
            }
            String getChineseName() {
                return "月";
            }
            int beginNum() {
                return 1;
            }
        },
        year {
            public int getUnit() {
                return 1;
            }
            int getCalendarField() {
                return Calendar.YEAR;
            }
            long getMillisNum() {
                return 1000 * 60 * 60 * 24 * 30 * 365;
            }
            String getChineseName() {
                return "年";
            }
            int beginNum() {
                return 1970;
            }
        },
        point {
            public int getUnit() {
                return 0;
            }
            int getCalendarField() {
                return 0;
            }
            long getMillisNum() {
                return 0;
            }
            String getChineseName() {
                return null;
            }
            int beginNum() {
                return 0;
            }
        };
        /** 单位 */
        abstract int getUnit();
        abstract int getCalendarField();
        /** 毫秒数 */
        abstract long getMillisNum();
        /** 中文名 */
        abstract String getChineseName();
        abstract int beginNum();
        public static TimeUnit getTimeUnitByName(String name) {
            if (StringHelper.isEmpty(name))
                return null;
            for (TimeUnit timeUnit : TimeUnit.values()) {
                if (timeUnit.name().equalsIgnoreCase(name))
                    return timeUnit;
            }
            return null;
        }
    }
    public static enum FormatUnit {
        yyyyMMddHHmmssSSS {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss:SSS";
            }
            public String getUnsignedValue() {
                return "yyyyMMddHHmmssSSS";
            }
        },
        yyyyMMddHHmmss {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
            public String getUnsignedValue() {
                return "yyyyMMddHHmmss";
            }
        },
        yyyyMMddHHmm {
            public String getValue() {
                return "yyyy-MM-dd HH:mm";
            }
            public String getUnsignedValue() {
                return "yyyyMMddHHmm";
            }
        },
        yyyyMMddHH {
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
            public String getUnsignedValue() {
                return "yyyyMMddHH";
            }
        },
        yyyyMMdd {
            public String getValue() {
                return "yyyy-MM-dd";
            }
            public String getUnsignedValue() {
                return "yyyyMMdd";
            }
        },
        yyyyMM {
            public String getValue() {
                return "yyyy-MM";
            }
            public String getUnsignedValue() {
                return "yyyyMM";
            }
        },
        HHmm {
            public String getValue() {
                return "HH:mm";
            }
            public String getUnsignedValue() {
                return "HHmm";
            }
        },
        HHmmss {
            public String getValue() {
                return "HH:mm:ss";
            }
            public String getUnsignedValue() {
                return "HHmmss";
            }
        },
        yyyy {
            public String getValue() {
                return "yyyy";
            }
            public String getUnsignedValue() {
                return "yyyy";
            }
        },
        MM {
            public String getValue() {
                return "MM";
            }
            public String getUnsignedValue() {
                return "MM";
            }
        },
        dd {
            public String getValue() {
                return "dd";
            }
            public String getUnsignedValue() {
                return "dd";
            }
        },
        HH {
            public String getValue() {
                return "HH";
            }
            public String getUnsignedValue() {
                return "HH";
            }
        },
        mm {
            public String getValue() {
                return "mm";
            }
            public String getUnsignedValue() {
                return "mm";
            }
        },
        ss {
            public String getValue() {
                return "ss";
            }
            public String getUnsignedValue() {
                return "ss";
            }
        },;
        public abstract String getValue(); // 抽象方法
        public abstract String getUnsignedValue(); // 无符号
        public String getFormatUnit(boolean isUnsigned) {
            if (!isUnsigned)
                return getValue();
            else
                return getUnsignedValue();
        }
    }
    public static enum Week {
        Monday("周一", Calendar.MONDAY), Tuesday("周二", Calendar.TUESDAY), Wednesday("周三", Calendar.WEDNESDAY), Thursday(
                "周四", Calendar.THURSDAY), Friday("周五",
                        Calendar.FRIDAY), Saturday("周六", Calendar.SATURDAY), Sunday("周日", Calendar.SUNDAY),;
        private String leab;
        private int dayNum;
        private Week(String leab, int dayNum) {
            this.leab = leab;
            this.dayNum = dayNum;
        }
        public static Week getWeekByDayNum(Integer value) {
            if (StringHelper.isEmpty(value)) {
                return null;
            }
            for (Week day : Week.values()) {
                if (day.getDayNum() == value) {
                    return day;
                }
            }
            return null;
        }
        public String getLeab() {
            return leab;
        }
        public int getDayNum() {
            return dayNum;
        }
    }
    
    public final static Date getDay(Date date,int amount) {
        return date_operation_to_date(getBegin_from_date_to_date(date, TimeUnit.day), TimeUnit.day, amount);
    }
    public final static String date_format_to_string(Date date, FormatUnit formatUnit, boolean isUnsigned) {
        if (date != null && formatUnit != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatUnit.getFormatUnit(isUnsigned));
            return format.format(date);
        }
        return null;
    }
    public final static String date_format_to_string(Date date, String formatString) {
        if (date != null && formatString != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            return format.format(date);
        }
        return null;
    }
    public final static Date date_format_to_date(Date date, FormatUnit formatUnit) {
        if (date != null && formatUnit != null) {
            return string_format_to_date(date_format_to_string(date, formatUnit, false), formatUnit, false);
        }
        return null;
    }
    public final static long date_format_to_millisecond(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        }
        return 0;
    }
    public final static long date_format_to_long(Date date, TimeUnit precision) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            long number = 0;
            calendar.setTime(date);
            number = calendar.getTimeInMillis();
            switch (precision) {
            case year:
                number = number / TimeUnit.month.getUnit();
            case month:
                number = number / TimeUnit.day.getUnit();
            case day:
                number = number / TimeUnit.hour.getUnit();
            case hour:
                number = number / TimeUnit.minute.getUnit();
            case minute:
                number = number / TimeUnit.second.getUnit();
            case second:
                number = number / TimeUnit.millisecond.getUnit();
            default:
                break;
            }
            return number;
        }
        return 0;
    }
    public final static Date date_set_to_date(Date date, TimeUnit precision, int amount) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.getMaximum(precision.getCalendarField()) >= amount && amount >= 0)
                calendar.set(precision.getCalendarField(), amount);
            return calendar.getTime();
        }
        return null;
    }
    public final static String date_operation_to_string(Date date, TimeUnit precision, int amount,
            FormatUnit formatUnit, boolean isUnsigned) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(precision.getCalendarField(), amount);
            return date_format_to_string(calendar.getTime(), formatUnit, isUnsigned);
        }
        return null;
    }
    public final static Date date_operation_to_date(Date date, TimeUnit precision, int amount) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(precision.getCalendarField(), amount);
            return calendar.getTime();
        }
        return null;
    }
    public final static long date_operation_to_long(Date date, TimeUnit precision, int amount) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(precision.getCalendarField(), amount);
            return calendar.getTimeInMillis();
        }
        return 0;
    }
    public static Timestamp date_foramt_to_timestamp(Date date, FormatUnit formatUnit, boolean isUnsigned) {
        return string_format_to_timestamp(date_format_to_string(date, formatUnit, isUnsigned));
    }
    public final static Date string_format_to_date(String date, FormatUnit formatUnit, boolean isUnsigned) {
        if (date != null && formatUnit != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatUnit.getFormatUnit(isUnsigned));
            ParsePosition pos = new ParsePosition(0);
            return format.parse(date, pos);
        }
        return null;
    }
    public final static String string_format_to_string(String date, FormatUnit formatUnit, boolean isUnsigned) {
        return date_format_to_string(string_format_to_date(date, formatUnit, isUnsigned), formatUnit, isUnsigned);
    }
    public final static String string_format_to_string(String date, FormatUnit from, boolean isUnsigned, FormatUnit to,
            boolean isUnsigned2) {
        return date_format_to_string(string_format_to_date(date, from, isUnsigned), to, isUnsigned2);
    }
    public final static long string_format_to_millisecond(String date, FormatUnit formatUnit, boolean isUnsigned) {
        Calendar calendar = Calendar.getInstance();
        Date day = string_format_to_date(date, formatUnit, isUnsigned);
        if (day != null) {
            calendar.setTime(day);
            return calendar.getTimeInMillis();
        }
        return 0;
    }
    public final static long string_format_to_long(String date, FormatUnit formatUnit, TimeUnit precision,
            boolean isUnsigned) {
        return date_format_to_long(string_format_to_date(date, formatUnit, isUnsigned), precision);
    }
    public final static Date string_operation_to_date(String day, FormatUnit formatUnit, TimeUnit precision, int amount,
            boolean isUnsigned) {
        return date_operation_to_date(string_format_to_date(day, formatUnit, isUnsigned), precision, amount);
    }
    public final static String string_operation_to_string(String day, FormatUnit formatUnit, TimeUnit precision,
            int amount, boolean isUnsigned) {
        return date_format_to_string(string_operation_to_date(day, formatUnit, precision, amount, isUnsigned),
                formatUnit, isUnsigned);
    }
    public final static String string_operation_to_string(String day, FormatUnit from, boolean isUnsigned,
            TimeUnit precision, int amount, FormatUnit to, boolean isUnsigned2) {
        return date_format_to_string(
                date_operation_to_date(string_format_to_date(day, from, isUnsigned), precision, amount), to,
                isUnsigned2);
    }
    public final static long string_operation_to_long(String date, FormatUnit formatUnit, TimeUnit precision,
            int amount, boolean isUnsigned) {
        if (!StringHelper.isEmpty(date) && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(string_format_to_long(date, formatUnit, precision, isUnsigned));
            calendar.add(precision.getCalendarField(), amount);
            return calendar.getTimeInMillis();
        }
        return 0L;
    }
    public final static Week getWeekByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Week.getWeekByDayNum(calendar.get(Calendar.DAY_OF_WEEK));
    }
    public final static Week getWeekByDate(String date, FormatUnit formatUnit, boolean isUnsigned) {
        return getWeekByDate(string_format_to_date(date, formatUnit, isUnsigned));
    }
    public final static Week getWeekByLong(Long time) {
        return getWeekByDate(long_format_to_date(time));
    }
    public final static Long getBegin_week_from_date_to_long(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime().getTime();
    }
    public final static boolean checkStringDate(String date, FormatUnit formatUnit, boolean isUnsigned) {
        try {
            DateFormat format = new SimpleDateFormat(formatUnit.getFormatUnit(isUnsigned));
            format.setLenient(false);
            format.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public final static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }
    public final static boolean isLeapYear(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return isLeapYear(calendar.get(Calendar.YEAR));
        }
        return false;
    }
    public final static boolean isLeapYear(long number) {
        if (number > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(number);
            return isLeapYear(calendar.get(Calendar.YEAR));
        }
        return false;
    }
    public final static String getNowString(FormatUnit formatUnit, boolean isUnsigned) {
        SimpleDateFormat format = new SimpleDateFormat(formatUnit.getFormatUnit(isUnsigned));
        return format.format(new Date());
    }
    public final static long getNowLong(TimeUnit precision) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        long number = 0;
        switch (precision) {
        case second:
            number = calendar.getTimeInMillis() / TimeUnit.millisecond.getUnit();
            break;
        case millisecond:
            number = calendar.getTimeInMillis();
        default:
            break;
        }
        return number;
    }
    public final static Date long_format_to_date(long number) {
        return new Date(number);
    }
    public final static String long_format_to_string(long number, FormatUnit formatUnit) {
        if (formatUnit != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatUnit.getValue());
            if (number <= 0) {
                return "";
            }
            return format.format(long_format_to_date(number));
        }
        return null;
    }
    public final static Date long_operation_to_date(long time, TimeUnit precision, int amount) {
        if (time > 0 && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            calendar.add(precision.getCalendarField(), amount);
            return calendar.getTime();
        }
        return null;
    }
    public final static Integer long_getField_to_int(long time, TimeUnit precision) {
        if (time > 0 && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            return calendar.get(precision.getCalendarField());
        }
        return null;
    }
    public final static long long_operation_to_long(long time, TimeUnit precision, int amount) {
        if (time > 0 && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            calendar.add(precision.getCalendarField(), amount);
            return calendar.getTimeInMillis();
        }
        return 0L;
    }
    public final static long long_setField_to_long(long time, TimeUnit precision, Integer amount) {
        if (time > 0 && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            if (amount == null) {
                amount = 0;
            }
            // 时间点的开始
            if (amount == 0) {
                // 如果未 年、月、日
                if (precision == TimeUnit.year || precision == TimeUnit.month || precision == TimeUnit.day) {
                    amount = 1;
                }
            } else if (amount == 29 && precision == TimeUnit.day && calendar.get(Calendar.MONTH) == 2
                    && !isLeapYear(time)) {
                // 时间是2月，设置的是29日，不是瑞年。则设置为28
                amount = 28;
            }
            calendar.set(precision.getCalendarField(), amount);
            return calendar.getTimeInMillis();
        }
        return 0L;
    }
    public final static Integer getField(Date time, TimeUnit precision) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(precision.getCalendarField());
    }
    public final static Date getBegin_from_date_to_date(Date date, TimeUnit precision) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar returnCal = Calendar.getInstance();
            returnCal.clear();
            switch (precision) {
            case millisecond:
                returnCal.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
            case second:
                returnCal.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
            case minute:
                returnCal.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            case hour:
                returnCal.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            case day:
                returnCal.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
            case month:
                returnCal.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            case year:
                returnCal.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            default:
                break;
            }
            return returnCal.getTime();
        }
        return null;
    }
    public final static long getBegin_from_date_to_long(Date date, TimeUnit precision) {
        return getBegin_from_date_to_date(date, precision).getTime();
    }
    public final static String getBegin_from_date_to_string(Date date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return date_format_to_string(getBegin_from_date_to_date(date, precision), formatUnit, isUnsigned);
    }
    public final static String getBegin_from_date_to_string(Date date, TimeUnit precision, FormatUnit formatUnit,
            int amount, TimeUnit optTimeUnit, boolean isUnsigned) {
        return date_format_to_string(
                getBegin_from_date_to_date(date_operation_to_date(date, optTimeUnit, amount), precision), formatUnit,
                isUnsigned);
    }
    public final static Date getBegin_from_long_to_date(long number, TimeUnit precision) {
        return getBegin_from_date_to_date(long_format_to_date(number), precision);
    }
    public final static long getBegin_from_long_to_long(long number, TimeUnit precision) {
        return getBegin_from_date_to_date(long_format_to_date(number), precision).getTime();
    }
    public final static String getBegin_from_long_to_string(long number, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return date_format_to_string(getBegin_from_long_to_date(number, precision), formatUnit, isUnsigned);
    }
    public final static String getBegin_from_string_to_string(String date, TimeUnit precision, FormatUnit formatUnit1,
            boolean isUnsigned1, FormatUnit formatUnit2, boolean isUnsigned2) {
        return getBegin_from_date_to_string(string_format_to_date(date, formatUnit1, isUnsigned1), precision,
                formatUnit2, isUnsigned2);
    }
    public final static Date getBegin_from_string_to_date(String date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return getBegin_from_date_to_date(string_format_to_date(date, formatUnit, isUnsigned), precision);
    }
    public final static long getBegin_from_string_to_long(String date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return getBegin_from_date_to_date(string_format_to_date(date, formatUnit, isUnsigned), precision).getTime();
    }
    public final static Date getEnd_from_date_to_date(Date date, TimeUnit precision) {
        if (date != null && precision != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar returnCal = Calendar.getInstance();
            returnCal.clear();
            switch (precision) {
            case millisecond:
                returnCal.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
            case second:
                returnCal.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
            case minute:
                returnCal.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
            case hour:
                returnCal.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
            case day:
                returnCal.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
            case month:
                returnCal.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            case year:
                returnCal.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            default:
                break;
            }
            returnCal.add(precision.getCalendarField(), 1);
            returnCal.add(Calendar.MILLISECOND, -1);
            return returnCal.getTime();
        }
        return null;
    }
    public final static long getEnd_from_date_to_long(Date date, TimeUnit precision) {
        return getEnd_from_date_to_date(date, precision).getTime();
    }
    public final static String getEnd_from_date_to_string(Date date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return date_format_to_string(getEnd_from_date_to_date(date, precision), formatUnit, isUnsigned);
    }
    public final static String getEnd_from_date_to_string(Date date, TimeUnit precision, FormatUnit formatUnit,
            int amount, TimeUnit optTimeUnit, boolean isUnsigned) {
        return date_format_to_string(
                getEnd_from_date_to_date(date_operation_to_date(date, optTimeUnit, amount), precision), formatUnit,
                isUnsigned);
    }
    public final static Date getEnd_from_long_to_date(long number, TimeUnit precision) {
        return getEnd_from_date_to_date(long_format_to_date(number), precision);
    }
    public final static long getEnd_from_long_to_long(long number, TimeUnit precision) {
        return getEnd_from_date_to_date(long_format_to_date(number), precision).getTime();
    }
    public final static String getEnd_from_long_to_string(long number, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return date_format_to_string(getEnd_from_long_to_date(number, precision), formatUnit, isUnsigned);
    }
    public final static String getEnd_from_string_to_string(String date, TimeUnit precision, FormatUnit formatUnit1,
            boolean isUnsigned1, FormatUnit formatUnit2, boolean isUnsigned2) {
        return getEnd_from_date_to_string(string_format_to_date(date, formatUnit1, isUnsigned1), precision, formatUnit2,
                isUnsigned2);
    }
    public final static Date getEnd_from_string_to_date(String date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return getEnd_from_date_to_date(string_format_to_date(date, formatUnit, isUnsigned), precision);
    }
    public final static long getEnd_from_string_to_long(String date, TimeUnit precision, FormatUnit formatUnit,
            boolean isUnsigned) {
        return getEnd_from_date_to_date(string_format_to_date(date, formatUnit, isUnsigned), precision).getTime();
    }
    public final static boolean isBeforeDate(Date sDate, Date eDate) {
        return compareDateVsDate(sDate, eDate) < EQUALLY;
    }
    public final static boolean isBeforeDate(Date sDate, Date eDate, FormatUnit formatUnit) {
        return compareDateVsDate(date_format_to_date(sDate, formatUnit),
                date_format_to_date(eDate, formatUnit)) < EQUALLY;
    }
    
    public final static boolean isAfterDate(Date sDate, Date eDate) {
        return compareDateVsDate(sDate, eDate) > EQUALLY;
    }
    public final static boolean isAfterDate(Date sDate, Date eDate, FormatUnit formatUnit) {
        return compareDateVsDate(date_format_to_date(sDate, formatUnit),
                date_format_to_date(eDate, formatUnit)) > EQUALLY;
    }
    public final static boolean isEqDate(Date sDate, Date eDate) {
        return compareDateVsDate(sDate, eDate) == EQUALLY;
    }
    public final static boolean isEqDate(Date sDate, Date eDate, FormatUnit formatUnit) {
        return compareDateVsDate(date_format_to_date(sDate, formatUnit),
                date_format_to_date(eDate, formatUnit)) == EQUALLY;
    }
    protected final static int compareDateVsDate(Date sDate, Date eDate) {
        int result = 0;
        Calendar sC = Calendar.getInstance();
        sC.setTime(sDate);
        Calendar eC = Calendar.getInstance();
        eC.setTime(eDate);
        result = sC.compareTo(eC);
        return result;
    }
    public final static int compareStringVsString(String sDate, String eDate, FormatUnit formatUnit,
            boolean isUnsigned) {
        return compareDateVsDate(string_format_to_date(sDate, formatUnit, isUnsigned),
                string_format_to_date(eDate, formatUnit, isUnsigned));
    }
    public final static int getDaysBetween(Date startDate, Date endDate) {
        startDate = getBegin_from_date_to_date(startDate, TimeUnit.day);
        endDate = getBegin_from_date_to_date(endDate, TimeUnit.day);
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(startDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(endDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }
    public final static String getdateDiff(Date startDate, Date endDate, TimeUnit precision) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(startDate);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(endDate);
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        String str = "";
        long diff = d2.getTimeInMillis() - d1.getTimeInMillis();
        switch (precision) {
        case millisecond:
            str = (diff > 0
                    ? String.valueOf(calculateNum(diff, TimeUnit.millisecond)) + TimeUnit.millisecond.getChineseName()
                    : "") + str;
        case second:
            str = (diff > 0 ? String.valueOf(calculateNum(diff, TimeUnit.second)) + TimeUnit.second.getChineseName()
                    : "") + str;
        case minute:
            str = (diff > 0 ? String.valueOf(calculateNum(diff, TimeUnit.minute)) + TimeUnit.minute.getChineseName()
                    : "") + str;
        case hour:
            str = (diff > 0 ? String.valueOf(calculateNum(diff, TimeUnit.hour)) + TimeUnit.hour.getChineseName() : "")
                    + str;
        case day:
            str = (diff > 0 ? String.valueOf(calculateNum(diff, TimeUnit.day)) + TimeUnit.day.getChineseName() : "")
                    + str;
            break;
        case month:
            return "精度错误";
        case year:
            return "精度错误";
        default:
            break;
        }
        return str;
    }
    public final static long calculateNum(long diff, TimeUnit precision) {
        if (diff <= 0)
            return diff;
        long divider = precision.getMillisNum();
        switch (precision) {
        case millisecond:
            diff = diff % TimeUnit.day.getMillisNum() % TimeUnit.hour.getMillisNum() % TimeUnit.minute.getMillisNum()
                    % TimeUnit.second.getMillisNum();
            break;
        case second:
            diff = diff % TimeUnit.day.getMillisNum() % TimeUnit.hour.getMillisNum() % TimeUnit.minute.getMillisNum();
            break;
        case minute:
            diff = diff % TimeUnit.day.getMillisNum() % TimeUnit.hour.getMillisNum();
            break;
        case hour:
            diff = diff % TimeUnit.day.getMillisNum();
            break;
        case day:
            break;
        default:
            return 0;
        }
        return diff / divider;
    }
    public final static long calculateNum(Date startDate, Date endDate, TimeUnit precision) {
        long diff = date_format_to_long(endDate, TimeUnit.millisecond)
                - date_format_to_long(startDate, TimeUnit.millisecond);
        if (diff <= 0)
            return 0;
        return diff / precision.getMillisNum();
    }
    public final static int getCalendarFieldValue(Date date, TimeUnit precision) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(precision.getCalendarField());
    }
    public final static long getNumByDate(Date date, TimeUnit[] timeUnits, TimeUnit precision) {
        long ret = 0L;
        for (TimeUnit timeUnit : timeUnits) {
            int tn = getCalendarFieldValue(date, timeUnit);
            ret += timeUnit_big_conversion_small(tn, timeUnit, precision);
        }
        return ret;
    }
    public final static long timeUnit_big_conversion_small(int num, TimeUnit big, TimeUnit small) {
        long ret = num;
        switch (big) {
        case year:
            if (small.ordinal() < TimeUnit.year.ordinal())
                ret = ret * TimeUnit.month.getUnit();
            else
                break;
        case month:
            if (small.ordinal() < TimeUnit.month.ordinal())
                ret = ret * TimeUnit.day.getUnit();
            else
                break;
        case day:
            if (small.ordinal() < TimeUnit.day.ordinal())
                ret = ret * TimeUnit.hour.getUnit();
            else
                break;
        case hour:
            if (small.ordinal() < TimeUnit.hour.ordinal())
                ret = ret * TimeUnit.minute.getUnit();
            else
                break;
        case minute:
            if (small.ordinal() < TimeUnit.minute.ordinal())
                ret = ret * TimeUnit.second.getUnit();
            else
                break;
        case second:
            if (small.ordinal() < TimeUnit.second.ordinal())
                ret = ret * TimeUnit.millisecond.getUnit();
            else
                break;
        case millisecond:
            break;
        default:
            break;
        }
        return ret;
    }
    public static boolean judgeTime(Date date, TimeUnit precision, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(precision.getCalendarField()) == num)
            return true;
        return false;
    }
    public static Timestamp string_format_to_timestamp(String data) {
        if (StringHelper.isEmpty(data))
            return null;
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(data);
        return ts;
    }
    public static Integer getAgeByBirthday(Date birthday) {
        if (birthday == null) {
            return null;
        }
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthday);
        int age = 0;
        Calendar now = Calendar.getInstance();
        age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH);
        int monthbirth = birth.get(Calendar.MONTH);
        if (monthNow <= monthbirth) {
            if (monthNow == monthbirth) {
                if (now.get(Calendar.DATE) < birth.get(Calendar.DATE)) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }
    
    
    /** 得到某年某周的第一天 */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }
    /** 得到某年某周的最后一天 */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }
    /** 取得当前日期所在周的第一天 */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }
    /** 取得当前日期所在周的最后一天 */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }
    /** 取得当前日期所在周的前一周最后一天 */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }
    /** 返回指定日期的月的第一天 */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }
    /** 返回指定年月的月的第一天 */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }
    /** 返回指定日期的月的最后一天 */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    /** 返回指定年月的月的最后一天 */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    /** 返回指定日期的上个月的最后一天 */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
    /** 返回指定日期的季的第一天 */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                                    getQuarterOfYear(date));
    }
    /** 返回指定年季的季的第一天 */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }
    /** 返回指定日期的季的最后一天 */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }
    /** 返回指定年季的季的最后一天 */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }
    /** 返回指定日期的上一季的最后一天 */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                                       getQuarterOfYear(date));
    }
    /** 返回指定年季的上一季的最后一天 */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }
    /** 返回指定日期的季度 */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }
    
    /** 获取某年第一天日期  */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 获取某年最后一天日期  */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
    
    /** 返回指定日期的年最后一天 */
    public static Date getLastDayOfYear(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return getYearLast(calendar.get(Calendar.YEAR));
    }
    
    public static Date getPreWeekDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
    		return getPreWeekDay(date_operation_to_date(date, TimeUnit.day, -1));
    	}
    	return date;
    }
    public static boolean isWorkDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        }
        return true;
    }
    public static boolean isWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }
    public static boolean isMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }
    public static boolean isQuarterEnd(Date date) {
        if(isEqDate(date, getFirstDayOfQuarter(date), FormatUnit.yyyyMMdd))
            return true;
        return false;
    }
    public static boolean isYearEnd(Date date) {
        if(isEqDate(date, getLastDayOfYear(date), FormatUnit.yyyyMMdd))
            return true;
        return false;
    }
    
    public static Map<String,Long> timesBetween(Date startdate,Date enddate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff = 0;
        try {
            startdate= df.parse(df.format(startdate));
            enddate= df.parse(df.format(enddate));
            long stime = startdate.getTime();
            long btime = enddate.getTime();
            if(stime>btime){
                diff = stime - btime;
            }else{
                diff = btime - stime;
            }
            day = diff/(24*60*60*1000);
            hour = diff/(60*60*1000);
            min = diff/(60*1000);
            sec = diff/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Long> timeMap = new HashMap<String, Long>();
        timeMap.put("Day", day);
        timeMap.put("Hour", hour);
        timeMap.put("Min", min);
        timeMap.put("Sec", sec);
        return timeMap;
    }
    
    public static Integer getDayBetween(Date startdate,Date enddate){
        Map<String,Long> map = timesBetween(startdate, enddate);
        return map.get("Day").intValue();
    }
    
    /** 获取下一个结束时间*/
    public static Date getNexEndDate(Date begin,Date end,TimeUnit precision, int num) {
    		if(null == begin || null == end)
    			return null;
    		Date tmp = date_operation_to_date(begin, precision, num);
    		if(isBeforeDate(tmp, end))
    			return tmp;
    		else
    			return end;
    }
}
