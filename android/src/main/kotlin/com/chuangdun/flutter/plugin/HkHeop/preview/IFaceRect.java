package com.chuangdun.flutter.plugin.HkHeop.preview;


interface IFaceRect {

    void onFaceDetect(Float left, Float top, Float width, Float height);

    void setDrawEnable(Boolean enable);

    void reverse(Boolean reverse);
}
