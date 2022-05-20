package com.chuangdun.flutter.plugin.HkHeop

import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.chuangdun.flutter.plugin.HkHeop.api.ApiService
import com.chuangdun.flutter.plugin.HkHeop.callback.ApiCallback
import com.chuangdun.flutter.plugin.HkHeop.libs.result.ApiResult
import com.chuangdun.flutter.plugin.HkHeop.model.*
import com.chuangdun.flutter.plugin.HkHeop.socket.GsonParse
import com.chuangdun.flutter.plugin.HkHeop.socket.HikSocket
import com.chuangdun.flutter.plugin.HkHeop.socket.JsonResultParse
import com.chuangdun.flutter.plugin.HkHeop.socket.SocketClient
import com.chuangdun.flutter.plugin.HkHeop.utils.XmlUtils
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/** HkHeopPlugin */
private const val TAG = "HkHeopPlugin"
private const val SDK_EVENT_REGISTRY_NAME = "HkHeopEvent"
private const val FACE_VIEW_REGISTRY_NAME = "HkFaceCameraView"
private const val EVENT_ON_ID_CARD_RECEIVED = 0
class HkHeopPlugin: FlutterPlugin, MethodChannel.MethodCallHandler, EventChannel.StreamHandler  {
  private lateinit var context: Context
  private lateinit var channel : MethodChannel
  private val uiHandler = Handler()
  private lateinit var threadPool:ThreadPoolExecutor
  private lateinit var eventChannel: EventChannel
  private  var hikSocket: HikSocket? = null
  private  var apiService:ApiService?  = null
  private var eventSink: EventChannel.EventSink? = null
  private var mMediaPlayer: MediaPlayer? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    threadPool = ThreadPoolExecutor(
            1, 1, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue<Runnable>())
    val viewFactory = HkFaceCameraViewFactory(context)
    flutterPluginBinding.platformViewRegistry.registerViewFactory(FACE_VIEW_REGISTRY_NAME, viewFactory)
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "HkHeop")
    channel.setMethodCallHandler(this)
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, SDK_EVENT_REGISTRY_NAME)
    eventChannel.setStreamHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    Log.i(TAG, "HkHeopPlugin:onMethodCall:${call.method}")
    when(call.method){
      "init" -> {
        hikSocket = HikSocket.builder().setResultProcessor(JsonResultParse(GsonParse())).setSocketClient(SocketClient()).build()
        apiService = this.hikSocket?.createService(ApiService::class.java)
      }
      "removeCallback" -> {
        val command = call.arguments as String
        hikSocket?.removeCallback(command)
      }
      "getCollectFingerprint" -> {
        val param: HashMap<String, Any> = LinkedHashMap()
        param["fingerPrintNo"] = 1
        apiService?.collectFingerPrint(param, object : ApiCallback<FingerprintInfo?>() {
          override fun onSuccess(apiResult: ApiResult<FingerprintInfo?>?) {
            if (apiResult != null && !"null".equals(apiResult.data)) {
              Log.d("heop::", "heopConsumptionMode: " + apiResult.data.toString())
              if (apiResult.data?.step == 3){
                result.success(mapOf(
                        "bitmap" to apiResult.data?.fpPic,
                        "feature" to apiResult.data?.fpTemplate
                ))
              }
            }
          }
          override fun onFailure(code: Int?) {
            result.error("500","指纹录入失败!错误码:$code",null)
          }
        })
      }
      "getAcsCfg" -> {
        apiService?.heopGetAcsCfg(object : ApiCallback<AcsCfgDataX?>() {

          override fun onFailure(code: Int) {
            super.onFailure(code)
            Log.d("heop::", "heopGetAcsCfg: $code")
            result.error("500","获取配置失败!错误码:$code",null)
          }

          override fun onSuccess(apiResult: ApiResult<AcsCfgDataX?>?) {
            if (apiResult != null) {
              Log.d("heop::", "heopGetAcsCfg: " + apiResult.data)
              result.success(mapOf(
                      "thermalEnabled" to apiResult.data?.acsCfg?.thermalEnabled,
                      "distanceUnit" to apiResult.data?.acsCfg?.distanceUnit,
                      "distance" to apiResult.data?.acsCfg?.distance,
                      "highestThermalThreshold" to apiResult.data?.acsCfg?.highestThermalThreshold,
                      "QRCodeEnabled" to apiResult.data?.acsCfg?.qrCodeEnabled,
                      "authType" to apiResult.data?.acsCfg?.authType
              ))
            }
          }
        })
      }
      "setAcsCfg" -> {
        val arguments = call.arguments as Map<*, *>
        val param: HashMap<String, Any?> = LinkedHashMap()
        param["thermalEnabled"] = arguments["thermalEnabled"]
        param["distanceUnit"] = arguments["distanceUnit"]
        param["distance"] = arguments["distance"]
        param["highestThermalThreshold"] = arguments["highestThermalThreshold"]
        param["lowestThermalThreshold"] = arguments["lowestThermalThreshold"]
        param["QRCodeEnabled"] = arguments["QRCodeEnabled"]
        /**
         * 人脸    0x20 32
         * 刷卡    0x02 2
         * 指纹    0x04 4
         * 人脸或刷卡   0x2a 42
         * 人脸或指纹   0x2c 44
         * 刷卡或指纹   0x0e 14
         * 人脸或刷卡或指纹  0x2e 46
         */
        param["authType"] = arguments["authType"]
        apiService?.heopSetAcsCfg(param, object : ApiCallback<Void?>() {

          override fun onFailure(code: Int) {
            super.onFailure(code)
            Log.d("heop::", "heopSetAcsCfg: $code")
          }

          override fun onSuccess(result: ApiResult<Void?>?) {
            if (result != null) {
              Log.d("heop::", "heopSetAcsCfg: " + result.data.toString())
            }
          }
        })
      }
      "getPictureAnalysis" -> {
        val filePath = call.arguments as String
        apiService?.heopPictureAnalysis("URL", filePath, "faceModel", object : ApiCallback<HeopResponse?>() {

          override fun onFailure(code: Int) {
            super.onFailure(code)
            Log.d("heop::", "heopPictureAnalysis: $code")
            result.error("500","图片建模失败!错误码:$code",null)
          }

          override fun onSuccess(apiResult: ApiResult<HeopResponse?>?) {
            if (apiResult != null) {
              Log.d("heop::", "heopPictureAnalysis: " + apiResult.data)
              if(apiResult.data?.targets?.size != 0){
                result.success(apiResult.data?.targets?.get(0)?.targetModelData)
              }
            }
          }
        })
      }
      "getCaptureFaceData" -> {
        val detectTask = Runnable {
          playSound(R.raw.start_detect_face, 4000)
          apiService?.heopFaceCollect(object : ApiCallback<String?>() {
            override fun onFailure(code: Int) {
              super.onFailure(code)
              Log.d("heop::", "heopFaceCollect: $code")
              result.error("500","获取人脸图片失败!错误码:$code",null)
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onSuccess(apiResult: ApiResult<String?>?) {
              if (apiResult != null) {
                Log.d("heop::", "heopFaceCollect: " + apiResult.data)
                playSound(R.raw.face_detected, 1500)
                if(apiResult.data != null){
                  val fields: ArrayList<String> = ArrayList()
                  fields.add("faceDataUrl")
                  fields.add("modelData")
                  val inputStream: InputStream = ByteArrayInputStream(apiResult.data?.toByteArray())
                  val lists: List<String>? = XmlUtils.parse(inputStream,fields)
                  result.success(mapOf(
                          "url" to (lists?.get(0) ?: ""),
                          "feature" to (lists?.get(1) ?: "")
                  ))
                }
              }
            }
          })
        }
        threadPool.execute(detectTask)
      }
      "faceComparison" -> {
        val arguments = call.arguments as Map<*, *>
        val targetImage = TargetImage()
        val contrastImage = ContrastImage()
        val targetImageCache:String = arguments["targetImageModel"] as String
        targetImage.targetModelData = targetImageCache
        val contrastImageCache:String = arguments["contrastImageModel"] as String
        contrastImage.targetModelData = contrastImageCache
        apiService?.heopImagesComparision("modelData", targetImage, contrastImage, object : ApiCallback<HeopResponse?>() {
          override fun onFailure(code: Int) {
            super.onFailure(code)
            Log.d("heop::", "heopImagesComparision: $code")
            result.error("500","对比失败!错误码:$code",null)
          }

          override fun onSuccess(apiResult: ApiResult<HeopResponse?>?) {
            if (apiResult != null) {
              result.success(apiResult.data?.similarity)
            }
          }
        })
      }
      "addIDCardCallback" -> {
        val detectTask = Runnable {
          playSound(R.raw.ic_card_began_read, 4000)
          //接收身份证事件
          apiService?.selfIdCardInfo(object : ApiCallback<EventData?>() {
            override fun onSuccess(result: ApiResult<EventData?>) {
              Log.d("heop::", "selfIdCardInfo: " + result.data)
              val cardInfo = result.data?.cardInfoEvent?.cardInfo
              playSound(R.raw.ic_card_success, 1800)
              uiHandler.post {
                eventSink?.success(mapOf(
                        "event" to EVENT_ON_ID_CARD_RECEIVED,
                        "IDCardInfo" to mapOf(
                                "name" to cardInfo?.name?.trim(),
                                "address" to cardInfo?.addr?.trim(),
                                "cardNo" to cardInfo?.cardNo?.trim(),
                                "sex" to cardInfo?.sex?.trim(),
                                "startDate" to cardInfo?.startDate?.trim(),
                                "endDate" to cardInfo?.endDate?.trim(),
                                "depart" to cardInfo?.issuingAuthority?.trim(),
                                "url" to result.data?.cardInfoEvent?.cardPicURL,
                                "nation" to cardInfo?.nation
                        )
                ))
              }
            }

            override fun onFailure(code: Int) {
              super.onFailure(code)
              Log.d("heop::", "selfIdCardInfo: $code")
              uiHandler.post {
                playSound(R.raw.ic_card_failure, 1800)
                eventSink?.error("500", "身份证读取失败!错误码:$code", null)
              }
            }
          })
        }
        threadPool.execute(detectTask)
      }
      else -> result.notImplemented()
    }
  }

  private fun playSound(resid:Int, waitMillis:Long){
    try {
      mMediaPlayer = MediaPlayer.create(context, resid)
      mMediaPlayer!!.start()
      Thread.sleep(waitMillis)
      mMediaPlayer!!.stop()
      mMediaPlayer!!.release()
    }catch (e: InterruptedException){
      Log.e(TAG, "线程睡眠waitMillis毫秒失败.${e.message}")
    }catch (e: Exception) {
      Log.e(TAG, "MediaPlayer错误.${e.message}")
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    eventChannel.setStreamHandler(null)
    if (!threadPool.isShutdown){
      threadPool.shutdownNow()
    }
  }


  override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
    this.eventSink = events
  }

  override fun onCancel(arguments: Any?) {
    this.eventSink = null
  }

}
