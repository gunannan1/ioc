package com.icbc.util;
import java.util.*;



public class Assert {

    private Assert(){}

    public static boolean isNotEmpty(Collection<?> c){
        return c != null && (!c.isEmpty());
    }

    public static boolean isNotNull(Object obj){
        return obj != null;
    }
    public static boolean isEffectiveString(String value) {
        return value != null && !value.equals("");
    }
}
