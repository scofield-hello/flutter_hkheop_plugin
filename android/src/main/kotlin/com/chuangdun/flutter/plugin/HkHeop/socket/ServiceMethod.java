package com.chuangdun.flutter.plugin.HkHeop.socket;



import com.chuangdun.flutter.plugin.HkHeop.callback.ApiCallback;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.Command;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.JustListen;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.Params;
import com.chuangdun.flutter.plugin.HkHeop.utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class ServiceMethod {
    private final Method method;
    private final HikSocket hikSocket;
    private final Annotation[] methodAnnotations;
    private final Annotation[][] parameterAnnotationsArray;
    private final Type[] parameterTypes;
    private final Object[] args;
    private Boolean isXml = false;

    private HashMap<String, Object> parameterHashMap =new HashMap<String, Object>();
    private String parameterString;


    private String command = null;
    Boolean justListen = null;

    ServiceMethod(HikSocket hikSocket, Method method, Object[] args){
        this.method = method;
        this.hikSocket = hikSocket;
        this.methodAnnotations= method.getAnnotations();
        this.parameterAnnotationsArray= method.getParameterAnnotations();
        this.parameterTypes=method.getGenericParameterTypes();
        this.args = args;
    }

    HashMap<String, Object> buildParams(){
        HashMap<String, Object> requestHashMap =new HashMap<String, Object>();

        for(Annotation annotation : methodAnnotations) {
             parseMethodAnnotation(annotation);
        }
        if (command == null || "".equals(command)) {
            throw new RuntimeException("command value is null.");
        }
        for (int p = 0; p < parameterAnnotationsArray.length; p++) {
                Type parameterType = parameterTypes[p];
                if (Utils.hasUnresolvableType(parameterType)) {
                    throw parameterError(p, "Parameter type must not include a type variable or wildcard: %s",
                            parameterType);
                }

                Annotation[] parameterAnnotations = parameterAnnotationsArray[p];

                parseParameter(p, parameterType, parameterAnnotations);
        }
        requestHashMap.put("command",command);
        if(isXml) {
            requestHashMap.put("params",parameterString);
            isXml = false;
        }
        else{
            requestHashMap.put("params",parameterHashMap);
        }
        return requestHashMap;
    }


    private void parseMethodAnnotation(Annotation annotation) {
        if (annotation instanceof Command) {
            command = parseSocketMethodAndCommand(((Command) annotation).value());
        } else if (annotation instanceof JustListen) {
            justListen = true;
        }
    }

    private String parseSocketMethodAndCommand(String value){
        if (value.isEmpty()) {
            throw new RuntimeException(" annotation value is null.");
        }
        return value;
    }

    private void parseParameter(int p, Type parameterType, Annotation[] annotations) {
        if (parameterType.toString().startsWith(ApiCallback.class.getName())) {
            parseParameterCallback(p, parameterType);
        } else {
            for (Annotation annotation : annotations) {
                parseParameterAnnotation(p, parameterType, annotation);
            }
        }
    }

    private void parseParameterAnnotation(int p, Type type, Annotation annotation) {
        if (annotation instanceof Params) {
            String name = ((Params) annotation).value();
            if("String".equals(name)) {
                isXml = true;
                parameterString = (String) args[p];
            }
            else{
                parameterHashMap.put(name,args[p]);
            }
        }
    }

    private void parseParameterCallback(int p, Type type) {
        Type responseType = Utils.getCallResponseType(type);
        hikSocket.addCallback(command, (ApiCallback<?>)args[p], responseType);
    }


    private RuntimeException methodError(String message, Object... args){
        return methodError(null, message, args);
    }

    private RuntimeException methodError(Throwable cause, String message, Object... args){
        String messageData = message;
        messageData = String.format(messageData, args);
        return new IllegalArgumentException(messageData
                + "\n    for method "
                + method.getDeclaringClass().getSimpleName()
                + "."
                + method.getName(), cause);
    }

    private RuntimeException parameterError(Throwable cause, int p, String message, Object... args){
        return methodError(cause, message + " (parameter #" + (p + 1) + ")", args);
    }

    private RuntimeException parameterError(int p, String message, Object... args){
        return methodError(message + " (parameter #" + (p + 1) + ")", args);
    }

}
