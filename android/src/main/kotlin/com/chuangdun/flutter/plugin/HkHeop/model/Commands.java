package com.chuangdun.flutter.plugin.HkHeop.model;

public class Commands {

    public static final String deviceStatus = "/device/deviceStatus";
    //获取ic卡信息，hicore主推
    public static final String selfIcCard = "/visitor/iccard";
    //获取二维码信息，hicore主推
    public static final String selfQrCode = "/visitor/qrcode";
    //获取身份证信息，hicore主推
    public static final String selfIdCard = "/visitor/idcard";
    //获取1:N信息
    public static final String self1N = "/identify/identifyResult";

    //获取设备信息
    public static final String HEOP_DeviceInfo = "/ISAPI/System/deviceInfo";

    //HEOP协议，获取设备信息能力
    public static final String HEOP_DeviceInfo_Capabilities = "/ISAPI/System/deviceInfo/capabilities";

    //HEOP协议，门禁补充指令
    public static final String HEOP_SupplyCmd = "/ISAPI/AccessControl/supplyCmd?format=json";

    //HEOP协议，门禁补充指令能力
    public static final String HEOP_SupplyCmd_Capabilities = "/ISAPI/AccessControl/supplyCmd/capabilities?format=json";

    //HEOP协议，门禁参数配置
    public static final String HEOP_AcsCfg = "/ISAPI/AccessControl/AcsCfg?format=json";

    //HEOP协议，门禁参数配置能力
    public static final String HEOP_AcsCfg_Capabilities = "/ISAPI/AccessControl/AcsCfg/capabilities?format=json";

    //HEOP协议，获取门禁主机工作状态
    public static final String HEOP_AcsWorkStatus = "/ISAPI/AccessControl/AcsWorkStatus?format=json";

    //HEOP协议，获取门禁主机工作状态能力
    public static final String HEOP_AcsWorkStatus_Capabilities = "/ISAPI/AccessControl/AcsWorkStatus/capabilities?format=json";

    //HEOP协议，获取前置摄像头抓拍图
    public static final String HEOP_Streaming0 = "/ISAPI/Streaming/channels/0/picture/async?format=json";

    //HEOP协议，获取底部摄像头抓拍图
    public static final String HEOP_Streaming2 = "/ISAPI/Streaming/channels/2/picture/async?format=json";

    //HEOP协议，获取抓拍参数
    public static final String HEOP_AccessControl = "/ISAPI/AccessControl/SnapConfig";

    //HEOP协议，获取抓拍参数能力
    public static final String HEOP_AccessControl_Capabilities = "/ISAPI/AccessControl/SnapConfig/capabilities";

    //HEOP协议，白光补光灯配置
    public static final String HEOP_Image1 = "/ISAPI/Image/channels/1/supplementLight";

    //HEOP协议，获取白光补光灯配置能力
    public static final String HEOP_Image1_Capabilities = "/ISAPI/Image/channels/1/supplementLight/capabilities";

    //HEOP协议，红外补光灯配置
    public static final String HEOP_Image2 = "/ISAPI/Image/channels/2/supplementLight";

    //HEOP协议，获取红外补光灯配置能力
    public static final String HEOP_Image2_Capabilities = "/ISAPI/Image/channels/2/supplementLight/capabilities";

    //HEOP协议，读卡器蜂鸣器和灯配置
    public static final String HEOP_RemoteControl = "/ISAPI/AccessControl/RemoteControl/buzzer/1?format=json";

    //HEOP协议，读卡器蜂鸣器和灯能力
    public static final String HEOP_RemoteControl_Capabilities = "/ISAPI/AccessControl/RemoteControl/buzzer/capabilities?format=json";

    //HEOP协议，读卡器参数配置
    public static final String HEOP_CardReaderCfg = "/ISAPI/AccessControl/CardReaderCfg/1?format=json";

    //HEOP协议，读卡器参数配置能力集
    public static final String HEOP_CardReaderCfg_Capabilities = "/ISAPI/AccessControl/CardReaderCfg/capabilities?format=json";

    //HEOP协议，获取人脸识别参数配置
    public static final String HEOP_FaceCompareCond = "/ISAPI/AccessControl/FaceCompareCond";

    //HEOP协议，获取人脸识别参数配置能力
    public static final String HEOP_FaceCompareCond_Capabilities = "/ISAPI/AccessControl/FaceCompareCond/capabilities";

    //HEOP协议，音频输出配置能力
    public static final String HEOP_AudioOut_Capabilities = "/ISAPI/System/Audio/AudioOut/channels/1/capabilities";

    //HEOP协议，音频输出配置
    public static final String HEOP_AudioOut = "/ISAPI/System/Audio/AudioOut/channels/1";

    //HEOP协议，人脸图片采集
    public static final String HEOP_CollectFace = "/ISAPI/AccessControl/CaptureFaceData";

    //HEOP协议，获取人脸采集能力
    public static final String HEOP_CaptureFace_Capabilities = "/ISAPI/AccessControl/CaptureFaceData/capabilities";

    //HEOP协议，人脸建模
    public static final String HEOP_PictureAnalysis = "/ISAPI/SDT/Face/pictureAnalysis?format=json";

    //HEOP协议，获取人脸图片分析能力（建模）
    public static final String HEOP_PictureAnalysis_Capabilities = "/ISAPI/SDT/Face/pictureAnalysis/capabilities?format=json";

    //HEOP协议，1v1人脸比对
    public static final String HEOP_ImagesComparision = "/ISAPI/Intelligent/imagesComparision/face?format=json";

    //HEOP协议，获取人脸1V1比对能力
    public static final String HEOP_ImagesComparision_Capabilities = "/ISAPI/Intelligent/imagesComparision/face/capabilities?format=json";

    //HEOP协议，TTS能力
    public static final String HEOP_TTS_Capabilities = "/ISAPI/HEOP/VideoIntercom/TTS/capabilities?format=json";

    //HEOP协议，TTS
    public static final String HEOP_TTS = "/ISAPI/HEOP/VideoIntercom/TTS?format=json";

    //HEOP协议，指纹采集能力
    public static final String HEOP_CaptureFingerPrint_Capabilities = "/ISAPI/AccessControl/CaptureFingerPrint/capabilities";

    //HEOP协议，指纹采集
    public static final String HEOP_CaptureFingerPrint = "/ISAPI/AccessControl/CaptureFingerPrint";

    //HEOP协议，指纹比对能力
    public static final String HEOP_FingerPrintComparision_Capabilities = "/ISAPI/HEOP/AccessControl/fingerPrintComparision/capabilities?format=json";

    //HEOP协议，指纹比对
    public static final String HEOP_FingerPrintComparision = "/ISAPI/HEOP/AccessControl/fingerPrintComparision?format=json";

    //HEOP协议，低功耗能力集
    public static final String HEOP_ConsumptionMode_Capabilities = "/ISAPI/System/consumptionMode/capabilities?format=json";

    //HEOP协议，低功耗
    public static final String HEOP_ConsumptionMode = "/ISAPI/System/consumptionMode?format=json";

    //HEOP协议，门禁总能力
    public static final String HEOP_Capabilities = "/ISAPI/AccessControl/capabilities";

    //HEOP协议，系统服务能力
    public static final String HEOP_System_Capabilities = "/ISAPI/System/capabilities";

    //HEOP协议，系统能力
    public static final String HEOP_HEOP_System_Capabilities = "/ISAPI/HEOP/System/capabilities?format=json";

    //HEOP协议，门禁能力
    public static final String HEOP_HEOP_AccessControl_Capabilities = "/ISAPI/HEOP/AccessControl/capabilities?format=json";

    //HEOP协议，对讲能力
    public static final String HEOP_HEOP_VideoIntercom_Capabilities = "/ISAPI/HEOP/VideoIntercom/capabilities?format=json";

    //HEOP协议，查询人员人脸指纹存在
    public static final String HEOP_FingerfaceInfo = "/ISAPI/AccessControl/FingerfaceInfo?format=json";

    //HEOP协议，指纹图片
    public static final String HEOP_FingerprintPic = "/ISAPI/AccessControl/FingerprintPic";

    //HEOP协议，采集指纹并返回图片
    public static final String HEOP_CollectFingerPrint = "/ISAPI/AccessControl/CollectFingerprint?format=json";

    //HEOP协议，配置1VN认证(成功失败）语音
    public static final String HEOP_VerifyTts_Config = "/ISAPI/HEOP/VideoIntercom/VerifyTTS?format=json";

    public static final String Verify_1VN = "/identify/identifyResult";
}