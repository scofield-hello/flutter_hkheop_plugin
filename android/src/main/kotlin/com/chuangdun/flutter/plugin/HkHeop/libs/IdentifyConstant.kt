package com.chuangdun.flutter.plugin.HkHeop.libs

interface IdentifyConstant {
    companion object {
        const val PREVIEW_PARAMS_ENABLE = 1 //开启预览
        const val PREVIEW_PARAMS_DISABLE = 0 //关闭预览
        const val PREVIEW_PARAMS_CAMERA_FRONT_NORMAL = 0 //前置正常
        const val PREVIEW_PARAMS_CAMERA_FRONT_INFRARED = 1 //前置红外
        const val PREVIEW_PARAMS_CAMERA_BOTTOM = 2 //底部摄像头
        const val PREVIEW_PARAMS_DISPLAY_MAIN = 0 //主屏幕
        const val PREVIEW_PARAMS_DISPLAY_SUB = 1 //副屏幕
    }
}