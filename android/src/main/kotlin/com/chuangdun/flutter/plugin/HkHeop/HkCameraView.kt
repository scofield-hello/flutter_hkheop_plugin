package com.chuangdun.flutter.plugin.HkHeop

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.chuangdun.flutter.plugin.HkHeop.preview.CameraPreviewLayout
import io.flutter.plugin.platform.PlatformView


private const val TAG = "HkFaceCameraView"

class HkFaceCameraView(private val context: Context,   createParams: Map<*, *>):PlatformView{


    private var linearLayout:CameraPreviewLayout? =null
    
    init {
        val cameraId = createParams["cameraId"] as Int
        val channelId = createParams["channelId"] as Int
        Log.d(TAG, "cameraId:$cameraId,channelId:$channelId")
//        linearLayout = ViewGroup.inflate(context,R.layout.widget_frame_layout,null) as FrameLayout
        linearLayout = CameraPreviewLayout(context, cameraId, channelId)
//        linearLayout.addView(previewLayout, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
    }

    override fun getView(): CameraPreviewLayout? {
        Log.i(TAG, "HkFaceCameraView:getView")
        return linearLayout
    }

    override fun onFlutterViewAttached(flutterView: View) {
        Log.i(TAG, "HkFaceCameraView:onFlutterViewAttached")
    }

    override fun onFlutterViewDetached() {
        Log.i(TAG, "HkFaceCameraView:onFlutterViewDetached")
    }

    override fun dispose() {
        Log.i(TAG, "HkFaceCameraView:dispose")
    }

    private fun dp2px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}
