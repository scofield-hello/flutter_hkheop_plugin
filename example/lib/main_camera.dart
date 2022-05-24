import 'package:HkHeop/HKCamera.dart';
import 'package:HkHeop_example/second.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class CameraPage extends StatefulWidget {
  @override
  _CameraPageState createState() => _CameraPageState();
}

class _CameraPageState extends State<CameraPage> with WidgetsBindingObserver {
  HkCameraViewController _controller;
  @override
  void initState() {
    print("camera page init");
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _controller = HkCameraViewController();
    _controller.onCameraOpened.listen((_) async {
      print("onCameraOpened");
    });
    _controller.onPhotoToken.listen((photoResult) {
      print(
          "拍摄高清照片:${photoResult.filePath}, 照片长度:${photoResult.imageData.length}");
    });
  }

  void _onHKCameraViewCreated() {
    print("_onHkCameraViewCreated");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("相机"),
        ),
        body: Container(
            child: Row(mainAxisSize: MainAxisSize.max, children: [
          Container(
            width: 1134,
            height: 800,
            color: Colors.black,
            alignment: Alignment.center,
            child: Container(
                width: 800,
                height: 600,
                alignment: Alignment.center,
                child: HkCameraView(
                    controller: _controller,
                    onHkCameraViewCreated: _onHKCameraViewCreated,
                    creationParams: HkCameraViewParams(
                        width: 640,
                        height: 480,
                        previewWidth: 2048,
                        previewHeight: 1536,
                        rotation: 90))),
          ),
          Column(
            children: [
              FlatButton(
                  onPressed: () async {
                    await _controller.takePicture("abc.jpg");
                  },
                  child: Text(
                    "拍照",
                  )),
              FlatButton(
                  onPressed: () async {
                    await _controller.clearCache();
                  },
                  child: Text(
                    "清除缓存",
                  )),
              FlatButton(
                  onPressed: () async {
                    await _controller.rotate(180);
                  },
                  child: Text(
                    "旋转180度",
                  )),
              FlatButton(
                  onPressed: () async {
                    await _controller.rotate(90);
                  },
                  child: Text(
                    "旋转90度",
                  )),
              FlatButton(
                  onPressed: () async {
                    await _controller.startPreview();
                  },
                  child: Text(
                    "开始预览",
                  )),
              FlatButton(
                  onPressed: () async {
                    await _controller.stopPreview();
                  },
                  child: Text(
                    "停止预览",
                  )),
              FlatButton(
                  onPressed: () async {
                    Navigator.of(context).push(
                        MaterialPageRoute(builder: (context) => SecondPage()));
                  },
                  child: Text(
                    "下一页",
                  )),
              FlatButton(
                  onPressed: () async {
                    Navigator.of(context).pop();
                  },
                  child: Text(
                    "上一页",
                  )),
            ],
          )
        ])));
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    switch (state) {
      case AppLifecycleState.resumed:
        print("didChangeAppLifecycleState:resume");
        _controller.startPreview();
        break;
      case AppLifecycleState.inactive:
        print("didChangeAppLifecycleState:inactive");
        break;
      case AppLifecycleState.paused:
        print("didChangeAppLifecycleState:pause");
        _controller.stopPreview();
        break;
      case AppLifecycleState.detached:
        print("didChangeAppLifecycleState:detached");
        break;
      default:
        break;
    }
  }

  @override
  void dispose() {
    print("camera page dispose");
    _controller.dispose();
    WidgetsBinding.instance.removeObserver(this);
    super.dispose();
  }
}
