package com.chuangdun.flutter.plugin.HkHeop.preview;

import android.graphics.RectF;
import android.os.Handler;
import android.view.Surface;

import com.chuangdun.flutter.plugin.HkHeop.libs.IdentifyConstant;
import com.chuangdun.flutter.plugin.HkHeop.utils.ThreadUtil;
import com.hik.common.HiAcs;
import com.hik.common.HiAcsCommon;

import java.util.HashMap;

public class HikCamera {
    //右侧130像素裁剪
    public static final int CAMERA_PREVIEW_WIDTH_CROP = 950;
    public static final int CAMERA_PREVIEW_WIDTH_SOURCE = 1080;
    public static final int CAMERA_PREVIEW_HEIGHT = 1920;
    //相机id
    private int mCameraId = 0;
    //屏幕Id
    private int mDisplayId = 0;

    private int mChannel = 0;
    private static Handler handler = new Handler();

    private static Runnable closeBottomCamera = () -> {
        ThreadUtil.runTaskInSerial(() -> {
            HiAcs.getInstance().closeCapt(IdentifyConstant.PREVIEW_PARAMS_CAMERA_BOTTOM);
        });
    };

    private HikCamera() {
    }

    private void setChannel(int mChannel) {
        this.mChannel = mChannel;
    }


    private void setCameraId(int cameraId) {
        this.mCameraId = cameraId;
    }

    private void setDisplayId(int displayId) {
        this.mDisplayId = displayId;
    }

    private static FaceDetectionExecutor faceDetectionExecutor = new FaceDetectionExecutor();

    static {
        setFaceDetectionListener(faceDetectionExecutor);
    }

    public boolean showSurface(int width, int height, Surface surface) {
        if (mChannel < 0) {
            return true;
        }
        boolean voShowDispOne = false;
        boolean voSetVi = false;
        boolean surfaceCrop = false;
        try {
            int h = CAMERA_PREVIEW_WIDTH_SOURCE * height / width;

            voSetVi = HiAcs.getInstance().setSurfaceCaptData(mCameraId, mDisplayId, mChannel);
            voShowDispOne = HiAcs.getInstance().setPreviewSurface(mDisplayId, mChannel, width, height, surface);
            surfaceCrop = HiAcs.getInstance().setSurfaceCrop(mDisplayId, mChannel, (CAMERA_PREVIEW_HEIGHT - h) / 2, 0, h, CAMERA_PREVIEW_WIDTH_SOURCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voShowDispOne && voSetVi && surfaceCrop;
    }

    public boolean setSurfaceCrop(int x, int y, int width, int height) {
        boolean surfaceCrop = false;
        try {
            surfaceCrop = HiAcs.getInstance().setSurfaceCrop(mDisplayId, mChannel, x, y, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return surfaceCrop;
    }

    public boolean stopPreview() {
        boolean voClearDispOne = false;
        boolean voClearSrcOne = false;
        boolean surfaceCrop = false;
        try {
            voClearDispOne = HiAcs.getInstance().cancelSurfaceData(mDisplayId, mChannel);
            voClearSrcOne = HiAcs.getInstance().cancelPreviewSurface(mDisplayId, mChannel);
            surfaceCrop = HiAcs.getInstance().cancelSurfaceCrop(mDisplayId, mChannel);
            if (mCameraId == IdentifyConstant.PREVIEW_PARAMS_CAMERA_BOTTOM) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(closeBottomCamera, 30_000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voClearDispOne && voClearSrcOne && surfaceCrop;
    }

    /* 设置人脸检测监听事件 */
    private static void setFaceDetectionListener(FaceDetectionListener listener) {
        HiAcs.getInstance().setFaceDetectionListener(rectFS -> {
            if (rectFS.length > 0) {
                listener.onFaceDetection(rectFS[0]);
            }
        });
    }

    public interface FaceDetectionListener {
        void onFaceDetection(RectF rectF);
    }

    public void addListener(String tag, FaceDetectionListener faceDetectionListener) {
        faceDetectionExecutor.addListener(tag, faceDetectionListener);
    }

    public boolean removeListener(String tag) {
        return faceDetectionExecutor.removeListener(tag);
    }

    public static class FaceDetectionExecutor implements FaceDetectionListener {

        HashMap<String, FaceDetectionListener> listenerHashMap = new HashMap<>();

        void addListener(String tag, FaceDetectionListener faceDetectionListener) {
            listenerHashMap.put(tag, faceDetectionListener);
        }

        boolean removeListener(String tag) {
            return listenerHashMap.remove(tag) != null;
        }

        @Override
        public void onFaceDetection(RectF rectF) {
            for (FaceDetectionListener value : listenerHashMap.values()) {
            value.onFaceDetection(new RectF(rectF.left, rectF.top, rectF.right, rectF.bottom));
        }
        }
    }

    Boolean startTextToSpeech(String content){
        Boolean surfaceCrop = false;
        try {
            HiAcsCommon.TtsParam ttsParam = new HiAcsCommon.TtsParam();
            ttsParam.txtContents = content.getBytes();
            ttsParam.volume = 80;
            ttsParam.priority = 10;
            ttsParam.txtEnc = "UTF8";
            ttsParam.customer = "xiaoxue";
            ttsParam.pitch = 50;
            ttsParam.speed = 50;
            ttsParam.rdn = 0;
            ttsParam.usageType = HiAcsCommon.UsageType.DIRECT_PLAY;
            surfaceCrop = HiAcs.getInstance().startTextToSpeech(new HiAcsCommon.TtsParam());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return surfaceCrop;
    }


    void setSurfaceMirror(HiAcsCommon.MirrorMode mirror) {
        try {
            HiAcs.getInstance().setSurfaceMirror(mDisplayId, mChannel, mirror);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private int cameraId = IdentifyConstant.PREVIEW_PARAMS_CAMERA_FRONT_NORMAL;
        private int displayId = IdentifyConstant.PREVIEW_PARAMS_DISPLAY_MAIN;
        private int channel = 0;

        public Builder channel(int channel) {
            this.channel = channel;
            return this;
        }


        public Builder cameraId(int cameraId) {
            this.cameraId = cameraId;
            return this;
        }

        public Builder displayId(int displayId) {
            this.displayId = displayId;
            return this;
        }

        public HikCamera build() {
           HikCamera camera = new HikCamera();
            camera.setCameraId(cameraId);
            camera.setDisplayId(displayId);
            camera.setChannel(channel);
            return camera;
        }
    }
}
