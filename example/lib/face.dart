import 'package:HkHeop/HkHeop.dart';
import 'package:flutter/material.dart';

class FacePage extends StatefulWidget {
  @override
  _FacePageState createState() => _FacePageState();
}

class _FacePageState extends State<FacePage> {
  HkHeop hkHeop;
  String fingerprint = "";
  String deviceInfo = "";
  String face =
      "SElLREZSQVJNdjIuMC4wUywgRQR2fwrbNLteIYDBU4pDbCvJEmW9E/KtPd0PSwLg8BnAPbYEIfJDEFIBODb949IABaL5Cw4tC+rqf///bdwf4Tf+3hcOlKcmLvXU9gIHz//RGCT9kNYNCCMxZIBqJQn7xfh3WkkL6NVo0xrzOKtVAvBQA+fBYSexYMxggNkP0gs4gN/kv97XMUbPQLBKl/N/6ZEd1D4vm1dDQv9DPNLUAYyEAjWyE3CU8e1hThV/2OXJO0Xh2eEKgLENHu7hwPTVD8E1DaWaxdB58BWluT8YnJPgQ+/a4hGTAlDlOtbnwdndf9cMEi35VwtANz3ODsk08E/t47/GRd4hDufu5EM=";
  String idCard =
      "SElLREZSQVJNdjIuMC4wUyDM3iB/PRfXQ8EOTLe+fohGdn+eYhcYAa+4GP3oMzoW8PPO+5+drs32L/X7f2s6qOSb07kR0BPK3y/KAXIDaFPI8g7yHggZAN8Af0Ju2w0fF8K8MSI659vE/NHfI4Aw6lkorOgIJyg60tx/z4P3Qr/m6hd/5hcNOhTsRPg9gBVJKSIvzixJy2HRQ2nsBP0t+80B3e4yAN9Li2ZMW8t6FR+zAYDqBBes223TEyx/PIkeHSdOfxMq3t+vgOr4SSQTDfOTG/dd8sH0gQIhRfjg7TQOyqlMKjjw6iO+PQvFGVAj6RAaf974Gc7/ZOsM4CWa3xQVSgkzGcb3+P8d97zDBPw=";

  @override
  void initState() {
    super.initState();
    hkHeop = HkHeop();
    hkHeop.init();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Face API TEST"),
        ),
        body: Row(
          children: [
            SizedBox(
              width: 640,
              height: 480,
              child: HkFaceCameraView(creationParams: HkFaceCameraViewParams()),
            ),
            Column(
              children: [
                Text(fingerprint),
                Text(deviceInfo),
                FlatButton(
                    onPressed: () {
                      hkHeop.addIDCardCallback();
                      hkHeop.onReceiveCardData.listen((event) {
                        setState(() {
                          fingerprint = event.name;
                        });
                      }).onError((error) {});
                    },
                    child: Text("?????????????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.removeCallback(Commands.selfIdCard);
                    },
                    child: Text("?????????????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.getCollectFingerprint().then((value) {
                        setState(() {
                          fingerprint = value.feature;
                        });
                      }).catchError((error) {});
                    },
                    child: Text("????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.getCaptureFaceData().then((value) {
                        setState(() {
                          fingerprint = value.url;
                        });
                      }).catchError((error) {});
                    },
                    child: Text("????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop
                          .getPictureAnalysis("/sdcard/face/idcard.bmp")
                          .then((value) {
                        setState(() {});
                      }).catchError((error) {});
                    },
                    child: Text("??????????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.faceComparison(face, idCard).then((value) {
                        setState(() {});
                      }).catchError((error) {});
                    },
                    child: Text("????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.getAcsCfg().then((value) {
                        setState(() {});
                      }).catchError((error) {});
                    },
                    child: Text("????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop
                          .setAcsCfg(AcsConfigInfo(authType: 32))
                          .then((value) {
                        setState(() {});
                      }).catchError((error) {});
                    },
                    child: Text("????????????")),
                FlatButton(
                    onPressed: () {
                      hkHeop.getDeviceInfo().then((value) {
                        String serialNumber = value.serialNumber;
                        setState(() {
                          deviceInfo = serialNumber;
                        });
                      }).catchError((error) {});
                    },
                    child: Text("??????????????????")),
              ],
            )
          ],
        ));
  }
}
