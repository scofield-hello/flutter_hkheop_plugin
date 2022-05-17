package com.chuangdun.flutter.plugin.HkHeop.socket;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.chuangdun.flutter.plugin.HkHeop.callback.Callback;
import com.chuangdun.flutter.plugin.HkHeop.model.Jsonlog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketClient {
    private SocketLoopThread loopThread;
    private ExecutorService executorService;

    public SocketClient(){
        init();
    }

    public static MutableLiveData<String> liveData = new MutableLiveData<>();

    public Callback callback;

    private void init() {
        executorService = Executors.newSingleThreadExecutor();
        loopThread = new SocketLoopThread(new SocketLoopThread.Callback() {
            public void onReceiveResponse(SocketResponse socketResponse){
                executorService.execute(new SocketMessage(socketResponse));
            }

            public void onReceiveFail(Exception e) {
                onFail();
            }
        });
        connect();
        startLoop();
    }


    private void connect() {
        executorService.submit(() -> {
            NativeSocket.getNativeSocket().connect();
        });
    }

    private void startLoop() {
        loopThread.startLoop();
    }

    public void send(final String iRequest){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.d("HIKDEMO", "send\n"+iRequest);
                Jsonlog send = new Jsonlog("send", iRequest);
                NativeSocket.getNativeSocket().unixSocketSend(iRequest);
                    Thread thread=new Thread(() -> liveData.postValue(send.toString()));
                thread.start();
                }
        });

        }



    void onReceive(SocketResponse response) {
        Log.d("HIKDEMO", "onreceive\n" + response.getBody());
        Jsonlog receive =new Jsonlog("receive", response.getBody());
        Thread thread=new Thread(() -> liveData.postValue(receive.toString()));
        thread.start();
        callback.invoke(response);
    }

    synchronized private void onFail() {
        NativeSocket.getNativeSocket().disconnect();
        NativeSocket.getNativeSocket().connect();
    }

     class SocketMessage implements Runnable {
        SocketResponse socketResponse;
        SocketMessage(SocketResponse socketResponse) {
             this.socketResponse = socketResponse;
         }
        public void run(){
            onReceive(socketResponse);
        }
    }


}
