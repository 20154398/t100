package com.ty.t100.util;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SSS");

    public String getTime(Date date){
        return sdf.format(date);
    }

    private static class SingletonHolder {
        private static final DateUtil INSTANCE = new DateUtil();
    }

    public static DateUtil getInstance() {
        return DateUtil.SingletonHolder.INSTANCE;
    }
}
