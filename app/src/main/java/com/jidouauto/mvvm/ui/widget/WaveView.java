package com.jidouauto.mvvm.ui.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;


/**
 * @author leosun.
 * @date 2018/8/12.
 */
public class WaveView extends BaseSurfaceView {

    /**
     * 逐帧变化的 图片资源，记得压缩
     */
    int[] mResArray = new int[]{

    };

    private Paint mPaintWave;

    private int mWidth;
    private int mHeight;
    private int mCenterX, mCenterY;
    private int mIndex;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onRender(Canvas canvas) {
        super.onRender(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        enforceInitial();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        //逐帧循环 播放
        int index = mIndex % 118;
        Bitmap bt = BitmapFactory.decodeResource(getResources(), mResArray[index]);
        canvas.drawBitmap(bt, mCenterX - bt.getWidth() / 2, mCenterY - bt.getHeight() / 2, mPaintWave);

        mIndex++;
    }

    void enforceInitial() {
        if (mWidth == 0) {
            mWidth = getWidth();
            mHeight = getHeight();
            mCenterX = (int) (mWidth / 2f);
            mCenterY = (int) (mHeight / 2f);
        }
    }

    private void initPaint() {
        mPaintWave = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

}
