package com.chuangdun.flutter.plugin.HkHeop.socket;

import android.text.TextUtils;

import com.chuangdun.flutter.plugin.HkHeop.api.ApiService;
import com.chuangdun.flutter.plugin.HkHeop.callback.ApiCallback;
import com.chuangdun.flutter.plugin.HkHeop.callback.Callback;
import com.chuangdun.flutter.plugin.HkHeop.libs.result.ISocketDataProcessor;
import com.chuangdun.flutter.plugin.HkHeop.utils.Utils;

import org.json.JSONException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;


public class HikSocket {

    private HashMap<String, ApiCallback<?>> callbackObjectMap = new HashMap<>();

    private HashMap<String, Type> callbackTypeMap = new HashMap<>();

    private Builder builder;

    private SocketClient socketClient;
    private ISocketDataProcessor iResultParse;

    private HikSocket(SocketClient socketClient, ISocketDataProcessor iResultParse) {
            this.socketClient = socketClient;
            this.iResultParse = iResultParse;
            socketClient.callback = new Callback() {
                @Override
                public void invoke(SocketResponse socketResponse) {
                    try {
                        parseFromReceive(socketResponse.getBody());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            };
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        ISocketDataProcessor iResultParse;
        SocketClient socketClient;

        public Builder setResultProcessor(ISocketDataProcessor iResultParse)  {
            this.iResultParse = iResultParse;
            return this;
        }

        public Builder setSocketClient(SocketClient socketClient) {
            this.socketClient = socketClient;
            return this;
        }

        public HikSocket build() {
            if (iResultParse == null) {
               throw new RuntimeException("must set ISocketDataProcessor");
            }
            if (socketClient == null) {
               throw new RuntimeException("SocketClient cannot be null");
            }
            HikSocket retrofit = new HikSocket(socketClient, iResultParse);
            retrofit.builder = this;
            return retrofit;
        }
    }

    public ApiService createService(Class service){
        Utils.validateServiceInterface(service);
        return (ApiService) Proxy.newProxyInstance(ApiService.class.getClassLoader(),new Class[]{ApiService.class},new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    parseToSend(method, args);
                    return null;
                }
        });
    }

    private void parseToSend(Method method, Object[] args) {
        ServiceMethod serviceMethod = new ServiceMethod(this, method, args);
        HashMap<String, Object> params = serviceMethod.buildParams();
        if(serviceMethod.justListen!=null){
            if(serviceMethod.justListen) {
                return;
            }
        }
        String message = iResultParse.processSendData(params);
        socketClient.send(message);
    }

    private Type getTypeByCommand(String command){
        return callbackTypeMap.get(command);
    }

    private void parseFromReceive(String response) throws JSONException {
        String command = iResultParse.processCommand(response);
        if (!TextUtils.isEmpty(command)) {
            Type type = getTypeByCommand(command);
            if (type != null) {
                ApiCallback callback = callbackObjectMap.get(command);
                callback.onReceive(iResultParse.processReceiveData(response, type));
            }
        }
    }

    void addCallback(String command, ApiCallback<?> callback , Type type) {
        callbackObjectMap.put(command,callback);
        callbackTypeMap.put(command,type);
    }

    public void removeCallback(String command) {
        callbackObjectMap.remove(command);
        callbackTypeMap.remove(command);
    }
}