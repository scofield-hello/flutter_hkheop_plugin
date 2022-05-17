package com.chuangdun.flutter.plugin.HkHeop.socket;


import com.chuangdun.flutter.plugin.HkHeop.libs.IJsonParse;
import com.chuangdun.flutter.plugin.HkHeop.libs.result.ApiResult;
import com.chuangdun.flutter.plugin.HkHeop.libs.result.ISocketDataProcessor;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class JsonResultParse implements ISocketDataProcessor {
    private IJsonParse iJsonParse;

    public JsonResultParse(IJsonParse iJsonParse){
        this.iJsonParse = iJsonParse;
    }

    public <T> ApiResult<T> processReceiveData(String response, Type type) throws JSONException {
        JSONObject responseJSONObject =new JSONObject(response);
        JSONObject resultJSONObject = responseJSONObject.getJSONObject("result");

        int code = resultJSONObject.getInt("code");
        boolean success = code == 200;
        String msg = resultJSONObject.getString("msg");

        JSONObject dataJSONObject;
        String dataJSONString;
        ApiResult<T> apiResult = new ApiResult<T>();
             try{
                 dataJSONObject = resultJSONObject.getJSONObject("data");
                 apiResult.setData((T) iJsonParse.parseObject(dataJSONObject.toString(), type));

             }
             catch(JSONException e) {
                 e.printStackTrace();
                 dataJSONString = resultJSONObject.getString("data");
                 apiResult.setData((T)dataJSONString);
             }

             apiResult.setCode(code);
             apiResult.setSuccess(success);
             apiResult.setMsg(msg);

            if (code == 200) {
                return apiResult;
            }
            else{
                apiResult.setData(null);
                return apiResult;
            }

    }

    public String processCommand(String response) throws JSONException {
        JSONObject responseJSONObject =new JSONObject(response);
        return responseJSONObject.getString("command");
    }

    public String processSendData(Object params)  {
        return iJsonParse.toJSONString(params);
    }
}