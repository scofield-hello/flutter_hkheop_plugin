import 'dart:async';
import 'dart:typed_data';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

///相机预览视图初始化参数.
class HkCameraViewParams {
  final int width;
  final int height;
  final int previewWidth;
  final int previewHeight;
  final int rotation;
  const HkCameraViewParams(
      {this.width = -1,
      this.height = -1,
      this.previewWidth = 1600,
      this.previewHeight = 1200,
      this.rotation = 90});

  Map<String, dynamic> asJson() {
    return {
      "width": width,
      "height": height,
      "previewWidth": previewWidth,
      "previewHeight": previewHeight,
      "rotation": rotation
    };
  }
}

///相机预览视图.
class HkCameraView extends StatelessWidget {
  final _viewType = "HkCameraView";
  final HkCameraViewParams creationParams;
  final HkCameraViewController controller;
  final VoidCallback onHkCameraViewCreated;
  const HkCameraView(
      {Key key,
      this.controller,
      this.onHkCameraViewCreated,
      this.creationParams = const HkCameraViewParams()})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AndroidView(
        viewType: _viewType,
        creationParams: creationParams.asJson(),
        creationParamsCodec: const StandardMessageCodec(),
        onPlatformViewCreated: _onPlatformViewCreated);
  }

  void _onPlatformViewCreated(int id) {
    if (controller != null) {
      controller.onCreate(id);
    }
    if (onHkCameraViewCreated != null) {
      onHkCameraViewCreated();
    }
  }
}

///相机预览尺寸.
class HkCameraPreviewSize {
  final int width;
  final int height;

  const HkCameraPreviewSize(this.width, this.height);

  Map<String, int> asJson() {
    return {"width": width, "height": height};
  }
}

///拍照结果对象.
class HkCameraCaptureResult {
  ///图片存储路径
  final String filePath;

  ///图片数据bytes
  final Uint8List imageData;
  const HkCameraCaptureResult(this.filePath, this.imageData);
}

///相机视图控制器
class HkCameraViewController {
  static const _EVENT_CHANNEL_NAME = "HkCameraViewEvent";
  static const _METHOD_CHANNEL_NAME = "HkCameraView";
  MethodChannel _methodChannel;
  EventChannel _eventChannel;

  void _onEvent(dynamic event) {
    switch (event['event']) {
      case 0:
        if (!_onCameraOpened.isClosed) {
          _onCameraOpened.add(null);
        }
        break;
      case 1:
        if (!_onPhotoToken.isClosed) {
          _onPhotoToken.add(
              HkCameraCaptureResult(event['filePath'], event['imageData']));
        }
        break;
      default:
        break;
    }
  }

  onCreate(int id) {
    _methodChannel = MethodChannel("${_METHOD_CHANNEL_NAME}_$id");
    _eventChannel = EventChannel("${_EVENT_CHANNEL_NAME}_$id");
    _eventChannel.receiveBroadcastStream().listen(_onEvent);
  }

  final _onCameraOpened = StreamController<void>.broadcast();

  ///相机打开时触发.
  Stream<void> get onCameraOpened => _onCameraOpened.stream;

  final _onPhotoToken = StreamController<HkCameraCaptureResult>.broadcast();

  ///调用[takePhoto]拍摄照片后触发.
  Stream<HkCameraCaptureResult> get onPhotoToken => _onPhotoToken.stream;

  ///返回相机是否开启.
  Future<bool> isCameraOpen() async {
    return _methodChannel.invokeMethod("isCameraOpen");
  }

  ///开始预览画面.
  Future<void> startPreview() async {
    _methodChannel.invokeMethod("startPreview");
  }

  ///关闭预览.
  Future<void> stopPreview() async {
    _methodChannel.invokeMethod("stopPreview");
  }

  ///清除相机缓存目录,包括照片所在目录.
  Future<void> clearCache() async {
    _methodChannel.invokeMethod("clearCache");
  }

  ///旋转预览画面角度.
  ///[rotation] 角度 默认90
  Future<void> rotate(int rotation) async {
    assert(rotation != null);
    _methodChannel.invokeMethod("rotate", rotation);
  }

  ///拍摄照片.
  ///[savePath]照片文件名.
  Future<void> takePicture(String savePath) async {
    assert(savePath != null);
    _methodChannel.invokeMethod("takePicture", savePath);
  }

  ///获取相机支持的分辨率列表.
  Future<List<HkCameraPreviewSize>> getSupportedPreviewSizes() async {
    List<dynamic> resolutions =
        await _methodChannel.invokeMethod("getSupportedPreviewSizes");
    return resolutions
        .map((e) => HkCameraPreviewSize(e['width'], e['height']))
        .toList();
  }

  ///切换分辨率.
  ///[width] 宽,[height] 高
  Future<void> updatePreviewSize(int width, int height) async {
    assert(width != null && height != null && width > 0 && height > 0);
    _methodChannel
        .invokeMethod("updatePreviewSize", {"width": width, "height": height});
  }

  void dispose() {
    _onCameraOpened.close();
    _onPhotoToken.close();
  }
}
