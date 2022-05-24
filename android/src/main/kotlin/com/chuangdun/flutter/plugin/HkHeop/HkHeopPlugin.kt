package com.chuangdun.flutter.plugin.HkHeop

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel


/** HkHeopPlugin */
private const val TAG = "HkHeopPlugin"
private const val SDK_EVENT_REGISTRY_NAME = "HkHeopEvent"
private const val FACE_VIEW_REGISTRY_NAME = "HkFaceCameraView"
const val CAMERA_VIEW_REGISTRY_NAME = "HkCameraView"
const val CAMERA_VIEW_EVENT_REGISTRY_NAME = "HkCameraViewEvent"

class HkHeopPlugin: FlutterPlugin, ActivityAware{
  private lateinit var methodChannel : MethodChannel
  private lateinit var eventChannel: EventChannel
  private lateinit var pluginBinding: FlutterPlugin.FlutterPluginBinding
  private var hkHeopApiHandler: HkHeopApiHandler? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Log.d(TAG, "onAttachedToEngine: init plugin and setup MethodCallHandler and StreamHandler")
    pluginBinding = flutterPluginBinding
    methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, "HkHeop")
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, SDK_EVENT_REGISTRY_NAME)
    hkHeopApiHandler = HkHeopApiHandler(flutterPluginBinding.applicationContext)
    methodChannel.setMethodCallHandler(hkHeopApiHandler)
    eventChannel.setStreamHandler(hkHeopApiHandler)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    Log.d(TAG, "onDetachedFromEngine: clear MethodCallHandler and StreamHandler")
    methodChannel.setMethodCallHandler(null)
    eventChannel.setStreamHandler(null)
    hkHeopApiHandler!!.release()
    hkHeopApiHandler = null
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    Log.d(TAG, "onAttachedToActivity: register ViewFactory")
    val cameraViewFactory = HkCameraViewFactory(binding.activity, pluginBinding.binaryMessenger)
    pluginBinding.platformViewRegistry.registerViewFactory(CAMERA_VIEW_REGISTRY_NAME, cameraViewFactory)
    val faceCameraViewFactory = HkFaceCameraViewFactory(binding.activity,pluginBinding.binaryMessenger)
    pluginBinding.platformViewRegistry.registerViewFactory(FACE_VIEW_REGISTRY_NAME, faceCameraViewFactory)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    Log.d(TAG, "onDetachedFromActivityForConfigChanges: do nothing")
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    Log.d(TAG, "onReattachedToActivityForConfigChanges: redo register ViewFactory")
    val cameraViewFactory = HkCameraViewFactory(binding.activity, pluginBinding.binaryMessenger)
    pluginBinding.platformViewRegistry.registerViewFactory(CAMERA_VIEW_REGISTRY_NAME, cameraViewFactory)
    val faceCameraViewFactory = HkFaceCameraViewFactory(binding.activity, pluginBinding.binaryMessenger)
    pluginBinding.platformViewRegistry.registerViewFactory(FACE_VIEW_REGISTRY_NAME, faceCameraViewFactory)
  }

  override fun onDetachedFromActivity() {
    Log.d(TAG, "onDetachedFromActivity: do nothing")
  }
}
