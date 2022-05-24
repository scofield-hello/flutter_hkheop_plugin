package com.chuangdun.flutter.plugin.HkHeop

import android.content.Context
import android.util.Log
import android.view.View
import com.chuangdun.flutter.plugin.HkHeop.preview.CameraPreviewLayout
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView


private const val TAG = "HkFaceCameraView"

class HkFaceCameraView(private val context: Context, messenger: BinaryMessenger,
                       id: Int, createParams: Map<*, *>):PlatformView{

    //private var relativeLayout: RelativeLayout?
    private var cameraPreviewLayout:CameraPreviewLayout? =null
    
    init {
        val cameraId = createParams["cameraId"] as Int
        val channelId = createParams["channelId"] as Int
        Log.d(TAG, "cameraId:$cameraId,channelId:$channelId")
        cameraPreviewLayout = CameraPreviewLayout(context, cameraId, channelId)

        /*relativeLayout = RelativeLayout(activity)
        uvcCameraTextureView = TextureView(activity)
        val layoutParams = RelativeLayout.LayoutParams(dp2px(activity, width.toFloat()),
            dp2px(activity, height.toFloat()))
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        uvcCameraTextureView!!.layoutParams = layoutParams*/
        cameraPreviewLayout!!.rotation = 270.0f
        //relativeLayout!!.addView(uvcCameraTextureView)
    }

    override fun getView(): CameraPreviewLayout? {
        Log.i(TAG, "HkFaceCameraView:getView")
        return cameraPreviewLayout
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
