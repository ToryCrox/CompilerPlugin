package com.hetaozs.tory.util;

import java.util.Collection;
import java.util.Map;

public class Utils {

    public static boolean isEmpty(String name) {
        return !isNotEmpty(name);
    }

    public static boolean isNotEmpty(CharSequence msg){
        return msg != null && msg.length() != 0;
    }


    public static boolean isNotEmpty(Collection collection){
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(Map map){
        return map != null && !map.isEmpty();
    }


}
