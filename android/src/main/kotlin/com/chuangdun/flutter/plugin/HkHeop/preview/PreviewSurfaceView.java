package com.chuangdun.flutter.plugin.HkHeop.preview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.chuangdun.flutter.plugin.HkHeop.callback.FaceRectCallback;
import com.chuangdun.flutter.plugin.HkHeop.utils.ThreadUtil;
import com.hik.common.HiAcsCommon;


public class PreviewSurfaceView extends SurfaceView {

    public PreviewSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public PreviewSurfaceView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public PreviewSurfaceView(Context context){
        super(context);
    }

    private SurfaceHolder mSurfaceHolder = null;

    private int mWidth = 0;
    private int mHeight = 0;
    HikCamera mCamera;

    public Boolean getisReady() {
        return  mWidth != 0 && mHeight != 0;
    }


    void setFaceRectCallback(String tag, FaceRectCallback faceRectCallback) {
        mCamera.addListener(tag, faceRectCallback::invoke);
    }

    Boolean removeFaceRectCallback(String tag){
        return mCamera.removeListener(tag);
    }

    void initConfig(int mChannel, int cameraId, int displayId) {
        mCamera = new HikCamera.Builder()
                .channel(mChannel)
                .cameraId(cameraId)
                .displayId(displayId)
                .build();
        mSurfaceHolder = getHolder();

        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder holder) {}

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                setSize(width, height);
                if (getisReady()) {
                    start();
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                setSize(0, 0);
                stop();
            }
        });

    }

     void setSurfaceMirror(HiAcsCommon.MirrorMode mirror) {
        ThreadUtil.runTaskInSerial(new Runnable() {
            @Override
            public void run() {
                mCamera.setSurfaceMirror(mirror);
            }
        });
     }

    void setSurfaceCrop(int x,int  y,int width,int height) {
        ThreadUtil.runTaskInSerial(new Runnable() {
            @Override
            public void run() {
                mCamera.setSurfaceCrop(x, y, width, height);
            }
        });
    }

    void startTextToSpeech(String content) {
        ThreadUtil.runTaskInSerial(new Runnable() {
            @Override
            public void run() {
                mCamera.startTextToSpeech(content);
            }
        });
    }

    /**
     * 暂停预览
     */
    private void stop() {
        ThreadUtil.runTaskInSerial(new Runnable() {
            @Override
            public void run() {  mCamera.stopPreview(); }
        });
    }

    /**
     * 开启预览
     *
     */
    private void start() {
        setRotation(90);
        ThreadUtil.runTaskInSerial(new Runnable() {
            @Override
            public void run() {
                mCamera.showSurface(mWidth, mHeight, mSurfaceHolder.getSurface()); }
            });
    }

    private void setSize(int width,int  height) {
        mWidth = width;
        mHeight = height;
    }


}