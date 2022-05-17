import 'package:HkHeop/HkHeop.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  HkHeop hkHeop;
  String name = "Plugin example app";
  String fingerprint = "";

  @override
  void initState() {
    super.initState();
    hkHeop = HkHeop();
    hkHeop.init();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: Text(name),
          ),
          body: Row(
            children: [
              Container(
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.black, width: 1.0),
                ),
                alignment: Alignment.center,
                width: 640,
                height: 480,
                child:
                    HkFaceCameraView(creationParams: HkFaceCameraViewParams()),
              ),
              Column(
                children: [
                  Text(fingerprint),
                  FlatButton(
                      onPressed: () {
                        hkHeop.addIDCardCallback();
                        hkHeop.onReceiveCardData.listen((event) {
                          setState(() {
                            fingerprint = event.name;
                          });
                        }).onError((error) {});
                      },
                      child: Text("设置身份证监听")),
                  FlatButton(
                      onPressed: () {
                        hkHeop.removeCallback(Commands.selfIdCard);
                      },
                      child: Text("关闭身份证监听")),
                  FlatButton(
                      onPressed: () {
                        hkHeop.getCollectFingerprint().then((value) {
                          setState(() {
                            name = value.feature;
                          });
                        }).catchError((error) {});
                      },
                      child: Text("获取指纹")),
                  FlatButton(
                      onPressed: () {
                        hkHeop.getCaptureFaceData().then((value) {
                          setState(() {
                            fingerprint = value.url;
                          });
                        }).catchError((error) {});
                      },
                      child: Text("获取人脸")),
                ],
              )
            ],
          )),
    );
  }
}
