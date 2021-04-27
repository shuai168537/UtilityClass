package com.longking.ws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * 自封装时间工具类，方便处理日常时间格式
 * @author yangxq
 */
public class BaseTimeUtil {

    /**
     * 获取当前日期 yyyy-MM-dd
     */
    public static String nowDateStr(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String nowDateTimeStr(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当月第一天日期
     */
    public static String firstDateMonthStr(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date firstDay = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(firstDay);
    }

    /**
     * 获取当月最后一天日期
     */
    public static String lastDateMonthStr(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,1);
        calendar.roll(Calendar.DATE,-1);
        Date lastDay = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(lastDay);
    }

    /**
     * 当前日期+天数
     */
    public static String addDays(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, n);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 当前日期+月份
     */
    public static String addMonths(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, n);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 当前日期+月份（第一天）
     */
    public static String addMonthsFirstDay(int n){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date firstDay = calendar.getTime();
        calendar.setTime(firstDay);
        calendar.add(Calendar.MONTH, n);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    /**
     * 指定时间+时分秒
     * type -> 1:时/2:分/3:秒
     */
    public static String addTimes(String str, int type, int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(str));
            switch (type){
                case 1:
                    calendar.add(Calendar.HOUR,n);
                    return sdf.format(calendar.getTime());
                case 2:
                    calendar.add(Calendar.MINUTE,n);
                    return sdf.format(calendar.getTime());
                case 3:
                    calendar.add(Calendar.SECOND,n);
                    return sdf.format(calendar.getTime());
                default:
                    return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据字符串获取日期
     * 参数：yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     */
    public static Date getDate(String str){
        Date date = null;
        try {
            if(str.length() == 10){
                date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                return date;
            }else if(str.length() == 19){
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
                return date;
            }else{
                return null;
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取毫秒级时间戳
     */
    public static Long milliSecondStamp(){
        return System.currentTimeMillis();
    }

    /**
     * 获取秒级时间戳
     */
    public static Long secondStamp(){
        return Instant.now().getEpochSecond();
    }

    /**
     * 把时间戳转为时间字符串
     */
    public static String formatStamp2Time(String currTime){
        if(currTime.length() == 10){
            currTime = currTime + "000";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(currTime));
    }

    /**
     * 获取两个时间之间的间隔
     * @param beginTime
     * @param endTime
     * @return *分钟/*小时*分钟/*天*小时*分钟
     */
    public static String timeStrInterval(String beginTime,String endTime){
        try{
            Long begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime).getTime();
            Long end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).getTime();
            String str = handleSubTime(begin,end);
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取两个时间之间的间隔
     * @param beginTime
     * @param endTime
     * @return *分钟/*小时*分钟/*天*小时*分钟
     */
    public static String timeInterval(Date beginTime,Date endTime){
        try{
            Long begin = beginTime.getTime();
            Long end = endTime.getTime();
            String str = handleSubTime(begin,end);
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    private static String handleSubTime(Long begin, Long end) {
        long l = end - begin;
        int m = (int)l/1000;
        int L = m/60;
        int min = 0;
        if (m%60 > 0) {
            min = 1;
        }
        String str = "";
        if(L>1440){
            int k = L / 1440;
            int h = L % 1440;
            int i = h / 60;
            int j = h % 60 + min;
            str = k + "天" + i + "小时" + j + "分钟";
        } else if (L>60){
            int i = L / 60;
            int j = L % 60 + min;
            str = i + "小时" + j + "分钟";
        } else {
            str = L + min +"分钟";
        }
        return str;
    }
}
