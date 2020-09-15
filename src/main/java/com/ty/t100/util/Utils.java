package com.ty.t100.util;

import java.util.Objects;

public class Utils {
    private static class SingletonHolder {
        private static final Utils INSTANCE = new Utils();
    }

    public static Utils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String deCode(String msg) {
        char[] c = msg.toCharArray();
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) (c[i] ^ 1997);
        }
        return new String(c);
    }

    public String enCode(String msg) {
        return deCode(msg);
    }

    public Boolean isNull(Object o) {
        if (o == null) return true;
        if (o instanceof String) {
            return ((String) o).length() == 0 || "null".equals(o);
        }
        return false;
    }
}
