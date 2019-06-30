package com.api.devtest.common;

import java.util.LinkedHashMap;

/*
    데이터 담아 사용하는 공통 Map
 */
public class DataMap extends LinkedHashMap {

    private String toProperCase(String s, boolean isCapital) {

        String rtnValue = "";

        if(isCapital){
            rtnValue = s.substring(0, 1).toUpperCase() +
                    s.substring(1).toLowerCase();
        }else{
            rtnValue = s.toLowerCase();
        }
        return rtnValue;
    }

    private String toCamelCase(String s){

        if(Character.isLowerCase(s.charAt(0))){
            return s;
        }

        String[] parts = s.split("_");
        StringBuilder camelCaseString = new StringBuilder();

        for (int i = 0; i < parts.length ; i++) {
            String part = parts[i];
            camelCaseString.append(toProperCase(part, (i != 0 ? true : false))) ;
        }

        return camelCaseString.toString();
    }

    @Override
    public Object put(Object key, Object value) {
        return super.put(toCamelCase((String)key), value);

    }

}