package com.chuangdun.flutter.plugin.HkHeop.model;

import com.google.gson.annotations.SerializedName;

public class QRCodeEvent{
    @SerializedName("QRCodeInfo")
    private String qrCodeInfo;

    public String getQrCodeInfo() {
        return qrCodeInfo;
    }

    public void setQrCodeInfo(String qrCodeInfo) {
        this.qrCodeInfo = qrCodeInfo;
    }

    @Override
    public String toString() {
        return "QRCodeEvent{" +
                "qrCodeInfo='" + qrCodeInfo + '\'' +
                '}';
    }
}
