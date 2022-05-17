package com.chuangdun.flutter.plugin.HkHeop.socket;




import android.util.Log;

import org.hik.localsocket.UnixSocket;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NativeSocket {

    private static final String TAG = "NativeSocket";
    private volatile int token = -1;

    private UnixSocket unixSocket;
    private static volatile NativeSocket nativeSocket;
    private final Lock mLock = new ReentrantLock(true);

    int receiveCapacity = 1024 * 300;

    Charset charset = StandardCharsets.UTF_8;

    public static NativeSocket getNativeSocket() {
        if (nativeSocket == null) {
            synchronized (NativeSocket.class) {
                if (nativeSocket == null) {
                    nativeSocket = new NativeSocket();
                }
            }
        }
        return nativeSocket;
    }

    private NativeSocket() {
        try {
            unixSocket = new UnixSocket();
        } catch (Exception e) {
            Log.e(TAG, "so文件加载异常");
        }
    }

    public boolean connect() {
        mLock.lock();
        Log.d(TAG,"unixSocketConnect 开始连接");
        try {
            token = unixSocket.unixSocketConnect();
            return token >= 0;
        } catch (Exception e) {
            Log.e(TAG, "unixSocketConnect异常");
        } finally {
            mLock.unlock();
        }
        return false;
    }

    private boolean isConnect() {
        boolean res;
        mLock.lock();
        res = token >= 0;
        mLock.unlock();
        return res;
    }

    public void disconnect() {
        mLock.lock();
        Log.d(TAG,"unixSocketConnect 断开连接");
        try {
            unixSocket.unixSocketDisConnect(token);
            token = -1;
        } catch (Exception e) {
            Log.e(TAG, "unixSocketDisConnect");
        } finally {
            mLock.unlock();
        }
    }


    public int unixSocketSend(String json) {
        return unixSocketSend(json.getBytes(charset));
    }

    private int unixSocketSend(byte[] bytes) {
        try {
            return unixSocket.unixSocketSend(token, bytes.length, bytes);
        } catch (Exception e) {
            Log.e(TAG, "unixSocketsend异常");
        }
        return -1;
    }

    public String unixSocketReceive() throws Exception {
        byte[] bytes = new byte[receiveCapacity];
        int len = unixSocket.unixSocketRecv(token, bytes);
        if (len > 0) {
            return new String(bytes, 0, len, charset);
        } else {
            throw new RuntimeException("socket disconnect");
        }
    }


}


