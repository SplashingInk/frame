package com.xtl.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转换成日期工具类
 * @author 31925
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {

        if (s == null) {
            throw new RuntimeException("请传入数据");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("数据类型转换错误");
        }
    }
}
