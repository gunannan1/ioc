package com.icbc.Util;
import java.util.*;

import java.util.Set;

public class Assert {

    private Assert(){}

    public static boolean isNullOrEmpty(Object obj){
        if (obj instanceof Object []){
            Object [] arr = (Object [])obj;
            if (arr.length ==0){
                return false;
            }return true;
        }else if (obj instanceof String) {
            String str = (String)obj;
            if (str.length()<=0||str==null){
                return false;
            }return true;
        }else if (obj instanceof List){
            List list = (List)obj;
            if (list==null ||list.isEmpty()){
                return false;
            }return true;
        }else if (obj instanceof Map){
            Map map = (Map)obj;
            if (map== null || map.isEmpty()){
                return false;
            }return true;
        }else if (obj instanceof Set){
            Set set = (Set)obj;
            if (set == null || set.isEmpty()){
                return false;
            }return true;
        }else{
            return obj==null?false:true;
        }
    }

    public static boolean isNotEmpty(Collection<?> c){
        return c != null && c.size()>0;
    }

    public static boolean isEffectiveString(String value) {
        return value != null && !value.equals("");
    }
}
