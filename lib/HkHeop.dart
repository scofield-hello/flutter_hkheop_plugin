import 'dart:async';
import 'dart:typed_data';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class FingerInfo {
  ///指纹特征.
  final String feature;

  ///指纹base64图像.
  final String bitmap;

  const FingerInfo(this.feature, this.bitmap)
      : assert(feature != null && feature.length > 0),
        assert(bitmap != null && bitmap.length > 0);
}

class FaceInfo {
  ///人脸建模值.
  final String feature;

  ///人脸图像路径.
  final String url;

  const FaceInfo(this.feature, this.url)
      : assert(feature != null && feature.length > 0),
        assert(url != null && url.length > 0);
}

class AcsConfigInfo {
  ///测温开关使能.
  final bool thermalEnabled;

  ///距离单位.
  final String distanceUnit;

  ///距离.
  final int distance;

  ///温度阈值上限.
  final double highestThermalThreshold;

  ///温度阈值下限.
  final double lowestThermalThreshold;

  ///二维码功能使能.
  final bool QRCodeEnabled;

  ///语音报警类型.
  /**
   * 人脸    0x20 32
   * 刷卡    0x02 2
   * 指纹    0x04 4
   * 人脸或刷卡   0x2a 42
   * 人脸或指纹   0x2c 44
   * 刷卡或指纹   0x0e 14
   * 人脸或刷卡或指纹  0x2e 46
   */
  final String authType;

  const AcsConfigInfo(
      {this.thermalEnabled,
        this.distanceUnit,
        this.distance,
        this.highestThermalThreshold,
        this.lowestThermalThreshold,
        this.QRCodeEnabled,
        this.authType});
}

class IDCardInfo {
  ///卡号
  final String id;

  ///姓名
  final String name;

  ///性别
  final String sex;

  ///民族
  final String nation;

  ///出生日期
  final String birth;

  ///地址
  final String address;

  ///签发机关
  final String depart;

  ///有效期起始时间
  final String startDate;

  ///有效期截止时间
  final String endDate;

  ///身份证图片url
  final String url;
  IDCardInfo(
      {this.id,
        this.name,
        this.sex,
        this.nation,
        this.birth,
        this.address,
        this.depart,
        this.startDate,
        this.endDate,
        this.url});
}

class HkHeopEventType {
  static const EVENT_ON_ID_CARD_RECEIVED = 0;
}

class Commands {
  ///获取设备信息
  static final String deviceStatus = "/device/deviceStatus";

  ///获取ic卡信息，hicore主推
  static final String selfIcCard = "/visitor/iccard";

  ///获取二维码信息，hicore主推
  static final String selfQrCode = "/visitor/qrcode";

  ///获取身份证信息，hicore主推
  static final String selfIdCard = "/visitor/idcard";

  ///获取1:N信息
  static final String self1N = "/identify/identifyResult";

  ///获取设备信息
  static final String HEOP_DeviceInfo = "/ISAPI/System/deviceInfo";

  ///获取设备信息能力
  static final String HEOP_DeviceInfo_Capabilities =
      "/ISAPI/System/deviceInfo/capabilities";

  ///门禁补充指令
  static final String HEOP_SupplyCmd =
      "/ISAPI/AccessControl/supplyCmd?format=json";

  ///门禁补充指令能力
  static final String HEOP_SupplyCmd_Capabilities =
      "/ISAPI/AccessControl/supplyCmd/capabilities?format=json";

  ///门禁参数配置
  static final String HEOP_AcsCfg = "/ISAPI/AccessControl/AcsCfg?format=json";

  ///门禁参数配置能力
  static final String HEOP_AcsCfg_Capabilities =
      "/ISAPI/AccessControl/AcsCfg/capabilities?format=json";

  ///获取门禁主机工作状态
  static final String HEOP_AcsWorkStatus =
      "/ISAPI/AccessControl/AcsWorkStatus?format=json";

  ///获取门禁主机工作状态能力
  static final String HEOP_AcsWorkStatus_Capabilities =
      "/ISAPI/AccessControl/AcsWorkStatus/capabilities?format=json";

  ///获取前置摄像头抓拍图
  static final String HEOP_Streaming0 =
      "/ISAPI/Streaming/channels/0/picture/async?format=json";

  ///获取底部摄像头抓拍图
  static final String HEOP_Streaming2 =
      "/ISAPI/Streaming/channels/2/picture/async?format=json";

  ///获取抓拍参数
  static final String HEOP_AccessControl = "/ISAPI/AccessControl/SnapConfig";

  ///获取抓拍参数能力
  static final String HEOP_AccessControl_Capabilities =
      "/ISAPI/AccessControl/SnapConfig/capabilities";

  ///白光补光灯配置
  static final String HEOP_Image1 = "/ISAPI/Image/channels/1/supplementLight";

  ///获取白光补光灯配置能力
  static final String HEOP_Image1_Capabilities =
      "/ISAPI/Image/channels/1/supplementLight/capabilities";

  ///红外补光灯配置
  static final String HEOP_Image2 = "/ISAPI/Image/channels/2/supplementLight";

  ///获取红外补光灯配置能力
  static final String HEOP_Image2_Capabilities =
      "/ISAPI/Image/channels/2/supplementLight/capabilities";

  ///读卡器蜂鸣器和灯配置
  static final String HEOP_RemoteControl =
      "/ISAPI/AccessControl/RemoteControl/buzzer/1?format=json";

  ///读卡器蜂鸣器和灯能力
  static final String HEOP_RemoteControl_Capabilities =
      "/ISAPI/AccessControl/RemoteControl/buzzer/capabilities?format=json";

  ///读卡器参数配置
  static final String HEOP_CardReaderCfg =
      "/ISAPI/AccessControl/CardReaderCfg/1?format=json";

  ///读卡器参数配置能力集
  static final String HEOP_CardReaderCfg_Capabilities =
      "/ISAPI/AccessControl/CardReaderCfg/capabilities?format=json";

  ///获取人脸识别参数配置
  static final String HEOP_FaceCompareCond =
      "/ISAPI/AccessControl/FaceCompareCond";

  ///获取人脸识别参数配置能力
  static final String HEOP_FaceCompareCond_Capabilities =
      "/ISAPI/AccessControl/FaceCompareCond/capabilities";

  ///音频输出配置能力
  static final String HEOP_AudioOut_Capabilities =
      "/ISAPI/System/Audio/AudioOut/channels/1/capabilities";

  ///音频输出配置
  static final String HEOP_AudioOut = "/ISAPI/System/Audio/AudioOut/channels/1";

  ///人脸图片采集
  static final String HEOP_CollectFace = "/ISAPI/AccessControl/CaptureFaceData";

  ///获取人脸采集能力
  static final String HEOP_CaptureFace_Capabilities =
      "/ISAPI/AccessControl/CaptureFaceData/capabilities";

  ///人脸建模
  static final String HEOP_PictureAnalysis =
      "/ISAPI/SDT/Face/pictureAnalysis?format=json";

  ///获取人脸图片分析能力（建模）
  static final String HEOP_PictureAnalysis_Capabilities =
      "/ISAPI/SDT/Face/pictureAnalysis/capabilities?format=json";

  ///1v1人脸比对
  static final String HEOP_ImagesComparision =
      "/ISAPI/Intelligent/imagesComparision/face?format=json";

  ///获取人脸1V1比对能力
  static final String HEOP_ImagesComparision_Capabilities =
      "/ISAPI/Intelligent/imagesComparision/face/capabilities?format=json";

  ///TTS能力
  static final String HEOP_TTS_Capabilities =
      "/ISAPI/HEOP/VideoIntercom/TTS/capabilities?format=json";

  ///TTS
  static final String HEOP_TTS = "/ISAPI/HEOP/VideoIntercom/TTS?format=json";

  ///指纹采集能力
  static final String HEOP_CaptureFingerPrint_Capabilities =
      "/ISAPI/AccessControl/CaptureFingerPrint/capabilities";

  ///指纹采集
  static final String HEOP_CaptureFingerPrint =
      "/ISAPI/AccessControl/CaptureFingerPrint";

  ///指纹比对能力
  static final String HEOP_FingerPrintComparision_Capabilities =
      "/ISAPI/HEOP/AccessControl/fingerPrintComparision/capabilities?format=json";

  ///指纹比对
  static final String HEOP_FingerPrintComparision =
      "/ISAPI/HEOP/AccessControl/fingerPrintComparision?format=json";

  ///低功耗能力集
  static final String HEOP_ConsumptionMode_Capabilities =
      "/ISAPI/System/consumptionMode/capabilities?format=json";

  ///低功耗
  static final String HEOP_ConsumptionMode =
      "/ISAPI/System/consumptionMode?format=json";

  ///门禁总能力
  static final String HEOP_Capabilities = "/ISAPI/AccessControl/capabilities";

  ///系统服务能力
  static final String HEOP_System_Capabilities = "/ISAPI/System/capabilities";

  ///系统能力
  static final String HEOP_HEOP_System_Capabilities =
      "/ISAPI/HEOP/System/capabilities?format=json";

  ///门禁能力
  static final String HEOP_HEOP_AccessControl_Capabilities =
      "/ISAPI/HEOP/AccessControl/capabilities?format=json";

  ///对讲能力
  static final String HEOP_HEOP_VideoIntercom_Capabilities =
      "/ISAPI/HEOP/VideoIntercom/capabilities?format=json";

  ///查询人员人脸指纹存在
  static final String HEOP_FingerfaceInfo =
      "/ISAPI/AccessControl/FingerfaceInfo?format=json";

  ///指纹图片
  static final String HEOP_FingerprintPic =
      "/ISAPI/AccessControl/FingerprintPic";

  ///采集指纹并返回图片
  static final String HEOP_CollectFingerPrint =
      "/ISAPI/AccessControl/CollectFingerprint?format=json";

  ///配置1VN认证(成功失败）语音
  static final String HEOP_VerifyTts_Config =
      "/ISAPI/HEOP/VideoIntercom/VerifyTTS?format=json";

  static final String Verify_1VN = "/identify/identifyResult";
}

class HkFaceCameraViewParams {
  final int channelId;
  final int cameraId;
  const HkFaceCameraViewParams(
      {
        this.cameraId = 0,
        this.channelId = 0});

  Map<String, dynamic> asJson() {
    return {
      "cameraId": cameraId,
      "channelId": channelId,
    };
  }
}

Map nations = {1:"汉",2:"蒙古",3:"回",4:"藏",5:"维吾尔",6:"苗",7:"彝",8:"壮",9:"布依",10:"朝鲜",11:"满",12:"侗",13:"瑶",14:"白",15:"土家"
  ,16:"哈尼",17:"哈萨克",18:"傣",19:"黎",20:"傈僳",21:"佤",22:"畲",23:"高山",24:"拉祜",25:"水",26:"东乡",27:"纳西",28:"景颇",29:"柯尔克孜"
  ,30:"土",31:"达斡尔",32:"仫佬",33:"羌",34:"布朗",35:"撒拉",36:"毛南",37:"仡佬",38:"锡伯",39:"阿昌",40:"普米",41:"塔吉克",42:"怒",43:"乌孜别克"
  ,44:"俄罗斯",45:"鄂温克",46:"德昂",47:"保安",48:"裕固",49:"京",50:"塔塔尔",51:"独龙",52:"鄂伦春",53:"赫哲",54:"门巴",55:"珞巴",56:"基诺"};


class HkFaceCameraView extends StatelessWidget {
  final _viewType = "HkFaceCameraView";
  final HkFaceCameraViewParams creationParams;
  const HkFaceCameraView(
      {Key key,
        this.creationParams = const HkFaceCameraViewParams()})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AndroidView(
        viewType: _viewType,
        creationParams: creationParams.asJson(),
        creationParamsCodec: const StandardMessageCodec());
  }
}

class HkHeop {
  static HkHeop _instance;
  static const _channel = const MethodChannel('HkHeop');
  static const _eventChannel = const EventChannel("HkHeopEvent");
  factory HkHeop() => _instance ?? HkHeop._();

  void _onEvent(dynamic event) {
    switch (event["event"]) {
      case HkHeopEventType.EVENT_ON_ID_CARD_RECEIVED:
        if (!_onReceiveCardData.isClosed) {
          Map map = event["IDCardInfo"];
          var nation ="";
          if(nations.containsKey(map["nation"])){
            nation = nations[map["nation"]];
          }else{
            nation = "未知";
          }
          _onReceiveCardData.add(IDCardInfo(id: map["cardNo"],name: map["name"],sex: map["sex"],address: map["address"],startDate: map["startDate"],depart: map["depart"],endDate:map["endDate"],url: map["url"],nation: nation ));
        }
        break;
    }
  }

  HkHeop._() {
    _eventChannel.receiveBroadcastStream().listen(_onEvent);
  }

  ///初始化模块.
  Future<void> init() async {
    await _channel.invokeMethod('init');
  }

  ///移除监听.
  Future<void> removeCallback(String command) async {
    await _channel.invokeMethod('removeCallback', command);
  }

  ///三次按压指纹.
  Future<FingerInfo> getCollectFingerprint() async {
    Map map = await _channel.invokeMethod('getCollectFingerprint');
    return FingerInfo(map["feature"],map["bitmap"]);
  }

  ///获取门禁配置.
  Future<AcsConfigInfo> getAcsCfg() async {
    return await _channel.invokeMethod('getAcsCfg');
  }

  ///设置门禁配置.
  ///[acsConfig] authType 人脸    0x20 32 刷卡    0x02 2 指纹    0x04 4 人脸或刷卡   0x2a 42 人脸或指纹   0x2c 44 刷卡或指纹  人脸或刷卡或指纹  0x2e 46
  Future<void> setAcsCfg(AcsConfigInfo acsConfig) async {
    assert(acsConfig != null, "配置信息不能为空.");
    await _channel.invokeMethod('setAcsCfg', acsConfig);
  }

  ///获取图片建模值.
  ///[url] 图片路径
  Future<String> getPictureAnalysis(String file) async {
    return await _channel.invokeMethod('getPictureAnalysis', file);
  }

  ///获取人脸图片.
  Future<FaceInfo> getCaptureFaceData() async {
    Map map = await _channel.invokeMethod('getCaptureFaceData');
    return FaceInfo(map["feature"], map["url"]);
  }

  ///人脸比对.
  ///[targetImageModel] 目标图片建模值
  ///[contrastImageModel] 对比图片建模值
  Future<FaceInfo> faceComparison(
      String targetImageModel, String contrastImageModel) async {
    return await _channel.invokeMethod('faceComparison', {
      "targetImageModel": targetImageModel,
      "contrastImageModel": contrastImageModel
    });
  }

  ///添加身份证读取监听.
  Future<void> addIDCardCallback() async {
    return await _channel.invokeMethod('addIDCardCallback');
  }

  final _onReceiveCardData = StreamController<IDCardInfo>.broadcast();

  ///获取到身份证信息时触发.
  Stream<IDCardInfo> get onReceiveCardData => _onReceiveCardData.stream;

  void dispose() {
    _onReceiveCardData.close();
  }
}
