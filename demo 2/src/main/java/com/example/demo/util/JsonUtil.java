package com.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtil<T> {

    /**
     * Object转JsonObject
     * @param obj
     * @return
     */
    public static JsonObject objToJsonObject(Object obj){
        Gson gson = new Gson();
        String s = gson.toJson(obj);
        return gson.fromJson(s, JsonObject.class);
    }

//    public static JsonObject objToClass(T data){
//        Gson gson = new Gson();
//        String s = gson.toJson(obj);
//        return gson.fromJson(s, JsonObject.class);
//    }

}
