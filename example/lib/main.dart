import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:HkHeop/HkHeop.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  HkHeop hkHeop;
  String name= "Plugin example app";

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
              child: HkFaceCameraView(creationParams:HkFaceCameraViewParams()),
            ),
            Column(
              children: [
                FlatButton(
                    onPressed: () {
                      hkHeop.addIDCardCallback();
                      hkHeop.onReceiveCardData.listen((event) {
                        setState(() {
                          name = event.name;
                        });

                      }).onError((error){

                      });
                    },
                    child: Text("设置身份证监听")),
                FlatButton(
                    onPressed: () {
                      hkHeop.removeCallback(Commands.selfIdCard);
                    },
                    child: Text("关闭身份证监听")),
                FlatButton(
                    onPressed: () {
                      hkHeop.getCollectFingerprint();
                    },
                    child: Text("获取指纹")),
              ],
            )
          ],
        )
      ),
    );
  }
}
