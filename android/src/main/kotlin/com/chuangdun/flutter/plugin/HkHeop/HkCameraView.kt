package com.chuangdun.flutter.plugin.HkHeop

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.usb.UsbDevice
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.RelativeLayout
import com.lgh.uvccamera.UVCCameraProxy
import com.lgh.uvccamera.bean.PicturePath
import com.lgh.uvccamera.callback.ConnectCallback
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import java.io.ByteArrayOutputStream


private const val TAG = "HkCameraView"

class HkCameraView(
    activity: Activity, messenger: BinaryMessenger, id: Int,
    createParams: Map<*, *>):PlatformView,
    MethodChannel.MethodCallHandler, EventChannel.StreamHandler{

    private val methodChannel = MethodChannel(messenger, "${CAMERA_VIEW_REGISTRY_NAME}_$id")
    private var eventChannel = EventChannel(messenger, "${CAMERA_VIEW_EVENT_REGISTRY_NAME}_$id")
    private var eventSink: EventChannel.EventSink? = null
    private var relativeLayout: RelativeLayout?
    private var uvcCameraTextureView: TextureView?
    private var uvcCameraProxy : UVCCameraProxy
    private var width = 0
    private var height = 0
    private var previewWidth = 0
    private var previewHeight = 0
    private var rotation = 90.0f
    init {
        Log.i(TAG, "HkCameraView init")
        width = createParams["width"] as Int
        height = createParams["height"] as Int
        previewWidth = createParams["previewWidth"] as Int
        previewHeight = createParams["previewHeight"] as Int
        rotation = (createParams["rotation"] as Int).toFloat()
        relativeLayout = RelativeLayout(activity)
        uvcCameraTextureView = TextureView(activity)
        val layoutParams = RelativeLayout.LayoutParams(dp2px(activity, width.toFloat()),
            dp2px(activity, height.toFloat()))
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        uvcCameraTextureView!!.layoutParams = layoutParams
        uvcCameraTextureView!!.rotation = rotation
        relativeLayout!!.addView(uvcCameraTextureView)
        methodChannel.setMethodCallHandler(this)
        eventChannel.setStreamHandler(this)
        uvcCameraProxy = UVCCameraProxy(activity)
        uvcCameraProxy.config.isDebug(true)
        uvcCameraProxy.config.picturePath = PicturePath.APPCACHE
        uvcCameraProxy.config.dirName = "uvccamera"
        uvcCameraProxy.setPreviewTexture(uvcCameraTextureView)
        uvcCameraProxy.setConnectCallback(object : ConnectCallback {
            override fun onAttached(usbDevice: UsbDevice) {
                uvcCameraProxy.requestPermission(usbDevice)
            }

            override fun onGranted(usbDevice: UsbDevice, granted: Boolean) {
                if (granted) {
                    uvcCameraProxy.connectDevice(usbDevice)
                }
            }

            override fun onConnected(usbDevice: UsbDevice) {
                uvcCameraProxy.openCamera()
            }

            override fun onCameraOpened() {
                eventSink?.success(mapOf("event" to 0))
                uvcCameraProxy.setPreviewSize(previewWidth, previewHeight)
                uvcCameraProxy.startPreview()
            }

            override fun onDetached(usbDevice: UsbDevice) {
                uvcCameraProxy.closeCamera()
            }
        })
        uvcCameraProxy.setPictureTakenCallback { filePath->
            run {
                Log.i(TAG, filePath)
                val bitmap = BitmapFactory.decodeFile(filePath)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                val data = outputStream.toByteArray()
                eventSink?.success(mapOf(
                    "event" to 1,
                    "filePath" to filePath,
                    "imageData" to data
                ))
            }
        }
    }

    override fun getView(): View {
        Log.i(TAG, "HkCameraView:getView")
        return relativeLayout!!
    }

    override fun onFlutterViewAttached(flutterView: View) {
        Log.i(TAG, "HkCameraView:onFlutterViewAttached")
    }

    override fun onFlutterViewDetached() {
        Log.i(TAG, "HkCameraView:onFlutterViewDetached")
    }

    override fun dispose() {
        Log.i(TAG, "HkCameraView:dispose")
        relativeLayout = null
        uvcCameraTextureView = null
    }

    private fun dp2px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }


    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        Log.i(TAG, "HkCameraView:onMethodCall:${call.method}")
        when(call.method){
            "startPreview" -> {
                uvcCameraProxy.startPreview()
            }
            "stopPreview" -> {
                uvcCameraProxy.stopPreview()
            }
            "rotate" -> {
                val rotation = call.arguments<Int>()
                uvcCameraProxy.setPreviewRotation(rotation.toFloat())
            }
            "takePicture" -> {
                val filePath = call.arguments<String>()
                uvcCameraProxy.takePicture(filePath)
            }
            "getSupportedPreviewSizes" -> {
                val resultList = mutableListOf<Map<String, Int>>()
                uvcCameraProxy.supportedPreviewSizes?.forEach { size ->
                    resultList.add(
                        mapOf(
                            "width" to size.width,
                            "height" to size.height
                        )
                    )
                }
                result.success(resultList)
            }
            "updatePreviewSize" -> {
                val width:Int = call.argument<Int>("width")!!
                val height:Int = call.argument<Int>("height")!!
                uvcCameraProxy.stopPreview()
                uvcCameraProxy.setPreviewSize(width, height)
                uvcCameraProxy.startPreview()
            }
            "clearCache" -> {
                uvcCameraProxy.clearCache()
            }
            "isCameraOpen" -> {
                result.success(uvcCameraProxy.isCameraOpen)
            }
        }
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        Log.i(TAG, "HkCameraView:onListen")
        this.eventSink = events
    }

    override fun onCancel(arguments: Any?) {
        Log.i(TAG, "HkCameraView:onCancel")
        this.eventSink = null
    }
}
