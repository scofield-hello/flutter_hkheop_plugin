package com.chuangdun.flutter.plugin.HkHeop.callback;


import com.chuangdun.flutter.plugin.HkHeop.libs.result.ApiResult;

/**
 * @author libingchen
 * @describe:
 * @date on 2021/1/6
 **/
interface BaseCallback<T> {

    void onReceive(ApiResult<T> result);
}