package com.iscopy.dailyenglish.utils;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/8/16.
 */

public class DateUtil {

    /**
     * 当前年月
     * @return
     */
    public static int getCurrentYearAndMonth(int yearOrMonth){
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        int day = a.get(Calendar.DAY_OF_MONTH);
        if(yearOrMonth == 3){
            return year;
        }else if(yearOrMonth == 2){
            return month;
        }else{
            return day;
        }
    }
    /**
     * 当前年、月、日
     * @return
     */
    public static String getCurrentYearAndMonth(){
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        int day = a.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 传入年月，返回这个月的最后一天（最大天数）
     * @param year
     * @param month
     * @return
     */
    public static int getCurrentMonthLastDay(int year, int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return lastDay;
    }

    /**
     * 获取当月第一天是星期几，星期日是第一天，依次类推
     * @param year
     * @param month
     * @return
     */
    public static int getFirstDayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        return i;
    }
}
