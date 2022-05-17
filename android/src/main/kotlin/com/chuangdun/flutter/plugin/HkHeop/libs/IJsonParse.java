package com.chuangdun.flutter.plugin.HkHeop.libs;
import java.lang.reflect.Type;

public interface IJsonParse {

    public String toJSONString(Object data);

    public <T> T parseObject(String json, Type type);
}