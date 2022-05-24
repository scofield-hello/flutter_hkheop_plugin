package com.chuangdun.flutter.plugin.HkHeop.preview;

import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.widget.FrameLayout;

import com.chuangdun.flutter.plugin.HkHeop.R;
import com.chuangdun.flutter.plugin.HkHeop.libs.IdentifyConstant;


public class CameraPreviewLayout extends FrameLayout {


    //private int layoutId = R.layout.widget_camera_preview;
    private Handler rectHandler = new Handler();
    int cameraId;
    int channelId ;
    int displayId ;
    String tag = "cm:$cameraId-ch:$channelId-ds:$displayId";
    PreviewSurfaceView cameraView;
    HikFaceRectView rectView;

    public CameraPreviewLayout(Context context, int cameraId, int channelId)
    {
        super(context);
        this.cameraId = cameraId;
        this.channelId = channelId;
        this.displayId = IdentifyConstant.PREVIEW_PARAMS_DISPLAY_MAIN;
        /*
        View.inflate(context, layoutId, this);
        cameraView = findViewById(R.id.cameraview);
        cameraView.setRotation(270);
        rectView = findViewById(R.id.rectview);*/
        cameraView = new PreviewSurfaceView(context);
        //cameraView.setRotation(90);
        FrameLayout.LayoutParams cameraViewLayoutParams = new FrameLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        cameraView.setLayoutParams(cameraViewLayoutParams);
        rectView = new HikFaceRectView(context);
        rectView.setLayoutParams(cameraViewLayoutParams);
        addView(cameraView);
        addView(rectView);
        cameraView.initConfig(cameraId, channelId, displayId);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        cameraView.setFaceRectCallback(tag, this::refreshRect);
    }

    void reverse(Boolean reverse){
        rectView.reverse(reverse);
    }

    private void refreshRect (RectF rect){
        int viewWidth = getWidth();
        if (viewWidth != 0) {
            float h = 1080f * getHeight() / getWidth();
            float topOffset = (1920f - h) / 2;
            rect.top = (rect.top * 1920 - topOffset) / h;

            rect.bottom = (rect.bottom * 1920) / h;
            rectView.onFaceDetect(rect.left, rect.top, rect.right, rect.bottom);
            rectHandler.removeCallbacksAndMessages(null);
            rectHandler.postDelayed(() -> rectView.onFaceDetect(0f, 0f, 0f, 0f), 300);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        rectHandler.removeCallbacksAndMessages(null);
        cameraView.removeFaceRectCallback(tag);
    }

}
