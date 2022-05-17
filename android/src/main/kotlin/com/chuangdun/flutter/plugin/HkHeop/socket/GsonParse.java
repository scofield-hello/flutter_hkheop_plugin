package com.chuangdun.flutter.plugin.HkHeop.socket;

import com.chuangdun.flutter.plugin.HkHeop.libs.IJsonParse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonParse implements IJsonParse {

    //bean ->  json
    @Override
    public String toJSONString(Object data) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        return gson.toJson(data);
    }


    //json -> bean
    @Override
    public <T> T parseObject(String json, Type type) {
        try {
            boolean xml = json.trim().startsWith("<");
            if (xml) {
                return (T)json;
            }
            return new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}