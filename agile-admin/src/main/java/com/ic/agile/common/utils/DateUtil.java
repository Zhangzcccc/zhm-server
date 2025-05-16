package com.ic.agile.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ZhangZc
 * @date: 2024/6/14 14:53
 * @description: TODO:
 */
public class DateUtil {

    public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_NUMBER = "yyyyMMddHHmmss";
    public static final String YEAR_MONTH_DAY_NUMBER = "yyyyMMdd";
    public static final String YEAR_MONTH_NUMBER = "yyyyMM";
    public static final String DATE_FORMAT_DAY_PATTERN = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY_EN_SECOND = "yyyy/MM/dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_CN_SECOND = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YEAR_MONTH_DAY_CN = "yyyy年MM月dd日";
    public static final String MONTH_DAY = "MM-dd";

    /**
     * 获取年月
     *
     * @param date 日期
     * @return yyyyMM
     */
    public static String getYearMonth(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(YEAR_MONTH_NUMBER).format(date);
    }

    /**
     * 获取年月
     *
     * @param date 日期
     * @return yyyyMM
     */
    public static String getYearMonth(String date) {
        if (date == null) {
            return null;
        }
        Date parse = new Date();
        try {
            parse = new SimpleDateFormat(DATE_FORMAT_DEFAULT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(YEAR_MONTH_NUMBER).format(parse);
    }

    /**
     * 获取年月
     *
     * @param date   日期
     * @param format format
     * @return format
     */
    public static String getYearMonth(String date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = DATE_FORMAT_DEFAULT;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}


