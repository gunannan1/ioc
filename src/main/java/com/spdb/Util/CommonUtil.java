package com.spdb.Util;
import	java.util.Set;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtil {

    private CommonUtil(){}

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


}
