package com.chuangdun.flutter.plugin.HkHeop.callback;

import android.util.Log;

import com.chuangdun.flutter.plugin.HkHeop.libs.result.ApiResult;
import com.chuangdun.flutter.plugin.HkHeop.utils.ThreadUtil;


public abstract class ApiCallback<T> implements BaseCallback<T> {

    @Override
    public void onReceive(final ApiResult<T> result) {
        ThreadUtil.postMain(new Runnable() {
            @Override
            public void run() {
                if (result.getSuccess()) {
                    Log.e("heop::","ApiCallback onSuccess");
                    onSuccess(result);
                } else {
                    Log.e("heop::","ApiCallback onFailure");
                    onFailure(result.getCode());
                }
            }
        });
    }

    protected abstract void onSuccess(ApiResult<T> result);

    protected void onFailure(Integer code){
         //do nothing
     }

}
