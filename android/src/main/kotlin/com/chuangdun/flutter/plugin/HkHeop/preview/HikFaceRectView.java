package com.chuangdun.flutter.plugin.HkHeop.preview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class HikFaceRectView extends View implements IFaceRect {

    public HikFaceRectView(Context context, AttributeSet attrs, int defStyleAttr)
    {
            super(context,attrs,defStyleAttr);
            init();
    }

    public HikFaceRectView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        init();
    }
    private float currentLeft = 0f;
    private float currentTop = 0f;
    private float currentWidth = 0f;
    private float currentHeight = 0f;
    private float rectLength = 34f;
    private float rectRadius = 9f;
    private float strokeWidth = 4f;

    private Paint paint;

    private boolean showing = false;

    private Boolean drawEnable = true;

    private Boolean reverse = true;
    private final PorterDuffXfermode xfermode =new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private final RectF faceRect =new RectF(currentLeft, currentTop, currentLeft + currentWidth, currentTop + currentHeight);
    
    private void init() {
        paint =new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        paint.setColor(-0x9b0);
    }



    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!showing || !drawEnable)
        {return;}

        faceRect.left = currentLeft;
        faceRect.top = currentTop;
        faceRect.right = currentLeft + currentWidth;
        faceRect.bottom = currentTop + currentHeight;

        canvas.drawRoundRect(faceRect, rectRadius, rectRadius, paint);
        if (currentWidth > 2 * rectLength) {
            paint.setXfermode(xfermode);
            paint.setStrokeWidth(strokeWidth * 2);
            canvas.drawRect(currentLeft + rectLength, currentTop, currentLeft + currentWidth - rectLength, currentTop + currentHeight, paint);
            paint.setXfermode(null);
        }
        if (currentHeight > 2 * rectLength) {
            paint.setXfermode(xfermode);
            paint.setStrokeWidth(strokeWidth * 2);
            canvas.drawRect(currentLeft, currentTop + rectLength, currentLeft + currentWidth, currentTop + currentHeight - rectLength, paint);
            paint.setXfermode(null);
        }
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    public void onFaceDetect(Float left, Float top, Float width, Float height) {
        if (left == 0f && top == 0f && width == 0f && height == 0f) {
            showing = false;
            postInvalidate();
            return;
        }
        if (!showing) {
            showing = true;
            postInvalidate();
            return;
        }
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        this.currentTop = top * viewHeight;
        this.currentWidth = width * viewWidth;
        this.currentHeight = height * viewHeight;
        if (reverse) {
            this.currentLeft = (float)viewWidth - left * viewWidth - currentWidth;
        } else {
            this.currentLeft = left * viewWidth;
        }
        postInvalidate();
    }

    @Override
    public void setDrawEnable(Boolean enable) {
        this.drawEnable = enable;
        invalidate();
    }

    @Override
    public void reverse(Boolean reverse) {
        this.reverse = reverse;
        invalidate();
    }
}