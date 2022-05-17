package com.chuangdun.flutter.plugin.HkHeop.api;


import com.chuangdun.flutter.plugin.HkHeop.callback.ApiCallback;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.Command;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.JustListen;
import com.chuangdun.flutter.plugin.HkHeop.libs.annotation.Params;
import com.chuangdun.flutter.plugin.HkHeop.model.AcsCfgDataX;
import com.chuangdun.flutter.plugin.HkHeop.model.Commands;
import com.chuangdun.flutter.plugin.HkHeop.model.ContrastImage;
import com.chuangdun.flutter.plugin.HkHeop.model.EventData;
import com.chuangdun.flutter.plugin.HkHeop.model.FingerprintInfo;
import com.chuangdun.flutter.plugin.HkHeop.model.HeopResponse;
import com.chuangdun.flutter.plugin.HkHeop.model.TargetImage;

import java.util.HashMap;

public interface ApiService {

    /**
     * 获取设备激活状态协议
     * 状态信息：0-未激活；1-已激活
     */
//    @Command(Commands.deviceStatus)
//    void getDeviceStatus(ApiCallback<DeviceStatus> callback);

    //HEOP协议，获取设备信息
    @Command(value = Commands.HEOP_DeviceInfo)
    void heopDeviceInfo(ApiCallback<String> callback);

    //HEOP协议，获取设备信息能力
    @Command(value = Commands.HEOP_DeviceInfo_Capabilities)
    void heopDeviceInfo_Capabilities(ApiCallback<String> callback);

    //HEOP协议，门禁补充指令
    @Command(value = Commands.HEOP_SupplyCmd)
    void heopSupplyCmd(@Params("SupplyCmd") HashMap<String, Object> supplyCmd, ApiCallback<Void> callback);

//    //HEOP协议，门禁补充指令能力
//    @Command(value = Commands.HEOP_SupplyCmd_Capabilities)
//    void heopSupplyCmdCapabilities(ApiCallback<SupplyCmdData> callback);

    //HEOP协议，获取门禁参数配置
    @Command(value = Commands.HEOP_AcsCfg)
    void heopGetAcsCfg(ApiCallback<AcsCfgDataX> callback);

    //HEOP协议，设置门禁参数配置
    @Command(value = Commands.HEOP_AcsCfg)
    void heopSetAcsCfg(@Params("AcsCfg") HashMap<String, Object> acsCfg, ApiCallback<Void> callback);

//    //HEOP协议，获取门禁参数配置能力
//    @Command(value = Commands.HEOP_AcsCfg_Capabilities)
//    void heopAcsCfgCapabilities(ApiCallback<AcsCfgData> callback);

//    //HEOP协议，获取门禁主机工作状态
//    @Command(value = Commands.HEOP_AcsWorkStatus)
//    void heopAcsWorkStatus(ApiCallback<AcsWorkStatusDataX> callback);
//
//    //HEOP协议，获取门禁主机工作状态能力
//    @Command(value = Commands.HEOP_AcsWorkStatus_Capabilities)
//    void heopAcsWorkStatusCapabilities(ApiCallback<AcsWorkStatusData> callback);
//
//    //HEOP协议，获取前置摄像头抓拍图
//    @Command(value = Commands.HEOP_Streaming0)
//    void heopStreaming0(ApiCallback<PictureDataData> callback);
//
//    //HEOP协议，获取底部摄像头抓拍图
//    @Command(value = Commands.HEOP_Streaming2)
//    void heopStreaming2(ApiCallback<PictureDataData> callback);

    //HEOP协议，获取抓拍参数
    @Command(value = Commands.HEOP_AccessControl)
    void heopGetAccessControl(ApiCallback<String> callback);

    //HEOP协议，获取抓拍参数
    @Command(value = Commands.HEOP_AccessControl)
    void heopSetAccessControl(@Params("String") String params, ApiCallback<Void> callback);

    //HEOP协议，获取抓拍参数能力
    @Command(value = Commands.HEOP_AccessControl_Capabilities)
    void heopAccessControl_Capabilities(ApiCallback<String> callback);

    //HEOP协议，获取白光补光灯配置
    @Command(value = Commands.HEOP_Image1)
    void heopGetImage1(ApiCallback<String> callback);

    //HEOP协议，设置白光补光灯配置
    @Command(value = Commands.HEOP_Image1)
    void heopSetImage1(@Params("String") String params, ApiCallback<Void> callback);

    //HEOP协议，获取白光补光灯配置能力
    @Command(value = Commands.HEOP_Image1_Capabilities)
    void heopImage1_Capabilities(ApiCallback<String> callback);

    //HEOP协议，获取红外补光灯配置
    @Command(value = Commands.HEOP_Image2)
    void heopGetImage2(ApiCallback<String> callback);

    //HEOP协议，设置红光补光灯配置
    @Command(value = Commands.HEOP_Image2)
    void heopSetImage2(@Params("String") String params, ApiCallback<Void> callback);

    //HEOP协议，获取红外补光灯配置能力
    @Command(value = Commands.HEOP_Image2_Capabilities)
    void heopImage2_Capabilities(ApiCallback<String> callback);

//    //HEOP协议，读卡器蜂鸣器和灯能力
//    @Command(value = Commands.HEOP_RemoteControl_Capabilities)
//    void heopRemoteControl_Capabilities(ApiCallback<RemoteControlBuzzerCapData> callback);

    //HEOP协议，读卡器蜂鸣器和灯配置
    @Command(value = Commands.HEOP_RemoteControl)
    void heopSetRemoteControl(@Params("RemoteControlBuzzer") HashMap<String, Object> remoteControlBuzzer, ApiCallback<Void> callback);

//    //HEOP协议，获取读卡器参数配置
//    @Command(value = Commands.HEOP_CardReaderCfg)
//    void heopGetCardReaderCfg(ApiCallback<CardReaderCfgData> callback);

//    //HEOP协议，读卡器参数能力
//    @Command(value = Commands.HEOP_CardReaderCfg_Capabilities)
//    void heopCardReaderCfg_Capabilities(ApiCallback<CardReaderCfgCapData> callback);

    //HEOP协议，配置读卡器
    @Command(value = Commands.HEOP_CardReaderCfg)
    void heopSetCardReaderCfg(@Params("CardReaderCfg") HashMap<String, Object> cardReaderCfg, ApiCallback<Void> callback);

    //HEOP协议，获取人脸识别参数配置
    @Command(value = Commands.HEOP_FaceCompareCond)
    void heopGetFaceCompareCond(ApiCallback<String> callback);

    //HEOP协议，设置人脸识别参数配置
    @Command(value = Commands.HEOP_FaceCompareCond)
    void heopSetFaceCompareCond(@Params("String") String params, ApiCallback<Void> callback);

    //HEOP协议，获取人脸识别参数配置能力
    @Command(value = Commands.HEOP_FaceCompareCond_Capabilities)
    void heopFaceCompareCond_Capabilities(ApiCallback<String> callback);

    //HEOP协议，音频输出配置能力
    @Command(value = Commands.HEOP_AudioOut_Capabilities)
    void heopAudioOut_Capabilities(ApiCallback<String> callback);

    //HEOP协议，音频输出配置
    @Command(value = Commands.HEOP_AudioOut)
    void heopGetAudioOut(ApiCallback<String> callback);

    //HEOP协议，设置音频输出配置
    @Command(value = Commands.HEOP_AudioOut)
    void heopSetAudioOut(@Params("String") String params, ApiCallback<Void> callback);

    //HEOP协议，人脸信息采集
    @Command(value = Commands.HEOP_CollectFace)
    void heopFaceCollect(ApiCallback<String> callback);

    //HEOP协议，获取人脸采集能力
    @Command(value = Commands.HEOP_CaptureFace_Capabilities)
    void heopCaptureFaceDataCapabilities(ApiCallback<String> callback);

//    //HEOP协议，获取人脸采集能力
//    @Command(value = Commands.HEOP_TTS_Capabilities)
//    void heopTTS_Capabilities(ApiCallback<TTSCapData> callback);

    //HEOP协议，设置TTS
    @Command(value = Commands.HEOP_TTS)
    void heopSetTTS(@Params("TTS") Object[] tTS, ApiCallback<Void> callback);

    //HEOP协议，指纹采集能力
    @Command(value = Commands.HEOP_CaptureFingerPrint_Capabilities)
    void heopCaptureFingerPrint_Capabilities(ApiCallback<String> callback);

    //HEOP协议，指纹采集
    @Command(value = Commands.HEOP_CaptureFingerPrint)
    void heopCaptureFingerPrint(@Params("String") String params, ApiCallback<Void> callback);

//    //HEOP协议，指纹比对能力
//    @Command(value = Commands.HEOP_FingerPrintComparision_Capabilities)
//    void heopFingerPrintComparision_Capabilities(ApiCallback<FingerPrintComparisionCapData> callback);

//    //HEOP协议，指纹比对
//    @Command(value = Commands.HEOP_FingerPrintComparision)
//    void heopFingerPrintComparision(@Params("FingerPrintComparision") FingerPrintComparision fingerPrintComparision, ApiCallback<Result> callback);

    //HEOP协议，设置低功耗
    @Command(value = Commands.HEOP_ConsumptionMode)
    void heopConsumptionMode(@Params("ConsumptionMode") HashMap<String, Object> consumptionMode, ApiCallback<Void> callback);

//    //HEOP协议，低功耗能力集
//    @Command(value = Commands.HEOP_ConsumptionMode_Capabilities)
//    void heopConsumptionMode_Capabilities(ApiCallback<ConsumptionModeData> callback);

    //HEOP协议，人脸建模
    @Command(value = Commands.HEOP_PictureAnalysis)
    void heopPictureAnalysis(@Params("imagesType") String imagesType, @Params("imagesData") String imagesData, @Params("algorithmType") String algorithmType, ApiCallback<HeopResponse> callback);

//    //HEOP协议，获取人脸图片分析能力（建模）
//    @Command(value = Commands.HEOP_PictureAnalysis_Capabilities)
//    void heopPictureAnalysisCapabilities(ApiCallback<PictureAnalysisData> callback);

    //HEOP协议，1v1人脸比对
    @Command(value = Commands.HEOP_ImagesComparision)
    void heopImagesComparision(@Params("dataType") String dataType, @Params("TargetImage") TargetImage targetImage, @Params("ContrastImage") ContrastImage contrastImage, ApiCallback<HeopResponse> callback);

//    //HEOP协议，获取人脸1V1比对能力
//    @Command(value = Commands.HEOP_ImagesComparision_Capabilities)
//    void heopImagesComparisionCapabilities(ApiCallback<ImagesComparisionData> callback);

//    //HEOP协议，HEOP系统能力
//    @Command(value = Commands.HEOP_HEOP_System_Capabilities)
//    void heopHEOP_HEOP_System_Capabilities(ApiCallback<SystemCapData> callback);
//
//    //HEOP协议，HEOP门禁能力
//    @Command(value = Commands.HEOP_HEOP_AccessControl_Capabilities)
//    void heopHEOP_HEOP_AccessControl_Capabilities(ApiCallback<AccessControlCapData> callback);
//
//    //HEOP协议，HEOP对讲能力
//    @Command(value = Commands.HEOP_HEOP_VideoIntercom_Capabilities)
//    void heopHEOP_VideoIntercom_Capabilities(ApiCallback<VideoIntercomCapData> callback);

    //HEOP协议，门禁总能力
    @Command(value = Commands.HEOP_Capabilities)
    void heopCapabilities(ApiCallback<String> callback);

    //HEOP协议，系统服务能力
    @Command(value = Commands.HEOP_System_Capabilities)
    void heopSystemCapabilities(ApiCallback<String> callback);

    //监听IC卡事件
    @JustListen
    @Command(value = Commands.selfIcCard)
    void selfIcCardInfo(ApiCallback<EventData> callback);

    //监听二维码事件
    @JustListen
    @Command(value = Commands.selfQrCode)
    void selfQrCodeInfo(ApiCallback<EventData> callback);

    //监听身份证事件
    @JustListen
    @Command(value = Commands.selfIdCard)
    void selfIdCardInfo(ApiCallback<EventData> callback);

//    //监听1:N事件
//    @JustListen
//    @Command(value = Commands.self1N)
//    void self1NInfo(ApiCallback<IdentifyResult1NData> callback);

//    //监听人员人脸指纹事件
//    @Command(value = Commands.HEOP_FingerfaceInfo)
//    void fingerFaceInfo(@Params("queryInfo") HashMap<String, Object> queryInfo, ApiCallback<FingerFaceInfoData> callback);

    //监听一次指纹图片事件
    @Command(value = Commands.HEOP_FingerprintPic)
    void fingerPrintPic(@Params("String") String params, ApiCallback<Void> callback);

    //监听三次指纹采集事件
    @Command(value = Commands.HEOP_CollectFingerPrint)
    void collectFingerPrint(@Params("fingerData") HashMap<String, Object> step, ApiCallback<FingerprintInfo> callback);

    //监听认证语音配置事件
    @Command(value = Commands.HEOP_VerifyTts_Config)
    void verifyTtsCfg(@Params("ttsCfg") HashMap<String, Object> step, ApiCallback<Void> callback);

    @Command(value = Commands.Verify_1VN)
    void verify1vn(ApiCallback<String> callback);


}