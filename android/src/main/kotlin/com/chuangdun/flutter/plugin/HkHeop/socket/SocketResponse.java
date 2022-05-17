package com.chuangdun.flutter.plugin.HkHeop.socket;

public class SocketResponse {
    private String result;

    SocketResponse(String result) {
        this.result = result;
    }
    public String getBody() {
        return result;
    }

}

