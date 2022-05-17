package com.chuangdun.flutter.plugin.HkHeop.libs.result;

import org.json.JSONException;

import java.lang.reflect.Type;

/**
 * @author renjianan
 * @describe:
 * @date on 2019/9/19 14:15
 **/
public interface ISocketDataProcessor {

    /**
     * 解析发送的数据
     */
    String processSendData(Object params);

    /**
     * 解析收到的结果
     */
    <T> ApiResult<T> processReceiveData(String response, Type type) throws JSONException;

    /**
     * 解析command
     */
    String processCommand(String response) throws JSONException;
}