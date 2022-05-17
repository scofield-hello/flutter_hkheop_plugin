package com.chuangdun.flutter.plugin.HkHeop.socket;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;

public class SocketLoopThread {

    private Handler handler;
    volatile private HandlerThread handlerThread = null;
    private Callback callback;
    SocketLoopThread(Callback callback) {
        this.callback = callback;
    }


    synchronized void startLoop() {
        if (handlerThread == null) {
            handlerThread = new HandlerThread("Loop-Thread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper(),new Handler.Callback(){
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    while (true) {
                        try {
                            String response = NativeSocket.getNativeSocket().unixSocketReceive();
                            callback.onReceiveResponse(new SocketResponse(response));
                        } catch (Exception e) {
                            callback.onReceiveFail(e);
                            handler.sendEmptyMessageDelayed(111, 2000);
                            break;
                        }
                    }
                    return true;
                }
            });
            handler.sendEmptyMessage(111);
        }
    }

    interface Callback {
        public void onReceiveResponse(SocketResponse socketResponse);

        public void onReceiveFail(Exception e);
    }
}
