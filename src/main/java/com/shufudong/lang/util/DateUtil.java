package com.shufudong.lang.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName: [中]DateUtil
 * @Description: [中]有关日期处理的工具类
 * @author [中]ShuFuDong
 * @date [中]2015年8月30日 下午2:27:18
 */
public class DateUtil {
    public static final long MILLIS_PER_SECOND = 1000;
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
    
    public static final String shortFormat = "yyyyMMdd";
    public static final String longFormat = "yyyyMMddHHmmss";
    public static final String webFormat = "yyyy-MM-dd";
    public static final String timeFormat = "HHmmss";
    public static final String monthFormat = "yyyyMM";
    public static final String chineseDtFormat = "yyyy年MM月dd日";
    public static final String newFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String noSecondFormat = "yyyy-MM-dd HH:mm";

    /**
     * 获取日期格式
     * 
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 获取当前日期格式：yyyy-MM-dd
     * 
     * @return
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(webFormat);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 根据传人格式，格式化传入时间
     * 
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     * 
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtil.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     * 
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(Date date, String format)
            throws ParseException {
        if (StringUtil.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        return dateFormat.parse(dateString, pos);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     * 
     * @param sDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate, String format)
            throws ParseException {
        if (StringUtil.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化传入时间：yyyyMMdd
     * 
     * @param sDate
     * @param delimit
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
            throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * 格式化传入时间：yyyy-MM-dd HH:mm:ss"
     * 
     * @param sDate
     * @return
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == newFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间
     * 
     * @param date
     * @param hours
     * 
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     * 
     * @param date
     * @param minutes
     * 
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 计算当前时间N秒之后的时间
     * 
     * @param date1
     * @param secs
     * 
     * @return
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + secs);
    }

    /**
     * 判断输入的字符串是否为合法的小时
     * 
     * @param hourStr
     * 
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtil.isEmpty(hourStr) && StringUtil.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     * 
     * @param minuteStr
     * 
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtil.isEmpty(str) && StringUtil.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     * 
     * @param date1
     *            日期
     * @param days
     *            天数
     * 
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * DateUtil.MILLIS_PER_DAY);
    }

    /**
     * 获取24小时后的时间
     * 
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, DateUtil.MILLIS_PER_DAY);

        return getDateString(aDate);
    }

    /**
     * 格式化传入时间：yyyyMMddHHmmss
     * 
     * @param date
     * @return
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 格式化传入时间：yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 获取当前时间字符串 传入格式
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * 获取24小时之前的时间
     * 
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -DateUtil.MILLIS_PER_DAY);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * 格式化时间：yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     * 
     * @param date
     * 
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取当天时间 格式shortFormat：yyyyMMdd
     * 
     * @return 格式化时间
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 格式化时间：HHmmss
     * 
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取n天之前的时间
     * 
     * @param days
     * @return
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis()
                - (DateUtil.MILLIS_PER_DAY * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     * 
     * @param one
     *            日期1
     * @param two
     *            日期2
     * 
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * 取得两个日期间隔分钟数（日期1-日期2）
     * 
     * @param one
     *            日期1
     * @param two
     *            日期2
     * 
     * @return 间隔秒数
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     * 
     * @param one
     * @param two
     * 
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取n天之前的时间 格式：yyyyMMdd
     * 
     * @param dateString
     * @param days
     * @return
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (DateUtil.MILLIS_PER_DAY * days));

        return df.format(date);
    }

    /**
     * 验证是否yyyyMMdd格式
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 验证是否yyyyMMdd格式
     * 
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate,
            String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtil.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn,
            DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * @param date1
     * @param date2
     * @param dateWebFormat2
     * 
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2,
            DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String formatTimeRange(Date startDate, Date endDate,
            String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtil.MILLIS_PER_DAY;
        long hour = (range % DateUtil.MILLIS_PER_DAY)
                / DateUtil.MILLIS_PER_HOUR;
        long minute = (range % DateUtil.MILLIS_PER_HOUR)
                / DateUtil.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     * 
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (DateUtil.MILLIS_PER_DAY));
    }

    /**
     * 获得指定时间当天起点时间
     * 
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }

    public static void main(String[] args) {
        System.out.println(getDayBegin(new Date()));
    }

    /**
     * 判断参date上min分钟后，是否小于当前时间
     * 
     * @param date
     * @param min
     * @return
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 判断是否早于当前时间
     * 
     * @param date
     *            要比较时间
     * @return
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 判断是否晚于当前时间
     * 
     * @param date
     *            要比较时间
     * @return
     */
    public static boolean isAfterNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) > 0;
    }

    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(noSecondFormat);

        if ((sDate == null) || (sDate.length() < noSecondFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtil.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 计算时间差
     * 
     * @param dBefor
     *            首日
     * @param dAfter
     *            尾日
     * @return 时间差(毫秒)
     */
    public static final long getDateBetween(Date dBefor, Date dAfter) {
        long lBefor = 0;
        long lAfter = 0;
        long lRtn = 0;

        /** 取得距离 1970年1月1日 00:00:00 GMT 的毫秒数 */
        lBefor = dBefor.getTime();
        lAfter = dAfter.getTime();

        lRtn = lAfter - lBefor;

        return lRtn;
    }

    /**
     * 加减天数
     * 
     * @param date
     * @return Date
     */
    public static final Date increaseDate(Date aDate, int days) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(aDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     * 获取传入时间当前小时
     * 
     * @param date
     * @return int
     */
    public static int getDateHours(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取传入时间当前分钟
     * 
     * @param date
     * @return int
     */
    public static int getDateMinutes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 日期格式化,针对日期的秒表示形式 by zengzhuo
     */
    public static String format(long second, String format) {
        if (format == null)
            format = "yyyy-MM-dd HH:mm:ss";
        Date d = new Date(second * 1000);
        return format(d, format);
    }

    public static String format(double second, String format) {
        long s = (long) second;
        return format(s, format);
    }

    /**
     * Date转为1970
     * 
     * @param time
     * @return
     */
    public static long to1970(Date time) {
        return time.getTime() / 1000;
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     * 
     * @param date
     *            需要得到24时间点的日期
     * @return 该日期24点的日期串,各时间点之间用“,”隔开
     */
    public static String get24TimesOfDay(Date date) {
        StringBuilder rv = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        for (int i = 0; i <= 23; i++) {
            c.set(Calendar.HOUR_OF_DAY, i);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            String t = String.valueOf(to1970(c.getTime()));
            rv.append(t);
            if (i < 23) {
                rv.append(",");
            }
        }

        return rv.toString();
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     * 
     * @param millis
     *            需要得到24时间点的日期
     * @return 该日期24点的日期串,各时间点之间用“,”隔开
     */
    public static String get24TimesOfDay(long second) {
        return get24TimesOfDay(new Date(second * 1000));
    }

    /**
     * 根据传入的日期,取得该日期24点的日期串,精确到小时
     * 
     * @param second
     * @return long[]
     */
    public static long[] get24TimesArrOfDay(long second) {
        String ts = get24TimesOfDay(second);
        String[] ta = ts.split(",");
        long[] l = new long[ta.length];
        for (int i = 0; i < ta.length; i++) {
            l[i] = Long.parseLong(ta[i]);
        }
        return l;
    }

    /**
     * 得到指定日期的零点零分零秒 返回表示秒数的long型数值
     */
    public static long getBeginOfDay(long second) {
        long[] l = DateUtil.get24TimesArrOfDay(second);
        return l[0];
    }

    /**
     * 得到指定日期的23点59分59秒
     */
    public static long getEndOfDay(long second) {
        long d = second * 1000;
        Date date = new Date(d);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis() / 1000;
    }
}
