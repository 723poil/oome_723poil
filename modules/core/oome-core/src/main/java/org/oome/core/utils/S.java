package org.oome.core.utils;

public class S {

    public static String f(String str, Object... objs) {

        for (int i = 0; i < objs.length; i ++) {
            if (!str.contains("{" + i + "}")) throw new IllegalArgumentException();
            String replaceStr = "";
            replaceStr = objs[i] instanceof String ? (String) objs[i] : objs[i].toString();
            str = str.replace("{" + i + "}", replaceStr);
        }

        return str;
    }
}
