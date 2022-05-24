import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:HkHeop_example/face.dart';
import 'package:HkHeop_example/camera.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        home: Scaffold(
          appBar: AppBar(
            title: Text("主页"),
          ),
          body: HomePage(),
        ));
  }
}

class HomePage extends StatefulWidget {
  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          FlatButton(
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) {
                  return CameraPage();
                }));
              },
              child: Text("相机程序")),
          FlatButton(
              onPressed: () {
                Navigator.of(context)
                    .push(MaterialPageRoute(builder: (context) {
                  return FacePage();
                }));
              },
              child: Text("人脸识别"))
        ],
      ),
    );
  }
}
