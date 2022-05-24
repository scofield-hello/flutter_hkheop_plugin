package com.chuangdun.flutter.plugin.HkHeop

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class HkCameraViewFactory(private val activity: Activity, private val messenger: BinaryMessenger) :
    PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context, viewId: Int, createParams: Any): PlatformView {
        return HkCameraView(activity, messenger, viewId, createParams as Map<*, *>)
    }
}