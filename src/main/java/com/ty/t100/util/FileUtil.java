package com.ty.t100.util;

import java.util.Date;

public class FileUtil {

    public String transformName(String name){
        String[] fileSplit = name.split("\\.");
        return DateUtil.getInstance().getTime(new Date()) + "." + fileSplit[fileSplit.length - 1];
    }

    private static class SingletonHolder {
        private static final FileUtil INSTANCE = new FileUtil();
    }

    public static FileUtil getInstance() {
        return FileUtil.SingletonHolder.INSTANCE;
    }
}
