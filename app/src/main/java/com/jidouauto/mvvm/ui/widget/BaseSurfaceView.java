package com.jidouauto.mvvm.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author leosun.
 * @date 2018/8/12.
 * 一个抽象的 surfaceview  具体绘制 操作需要继承 扩展
 * 比如说 ：绘制一个 跟随音量大小 变化的view
 * 继承BaseSurfaceView  然后实现onRender 方法，可以自定义 音量属性 对外暴露接口
 */
public abstract class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "BaseSurfaceView";
    private final Object surfaceLock = new Object();
    private DrawThread drawThread;

    public BaseSurfaceView(Context context) {
        this(context, null);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(holder);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        synchronized (surfaceLock) {
            drawThread.setRun(false);
        }
    }

    private void render(Canvas canvas) {
        onRender(canvas);
    }

    /**
     * 具体实现 绘制逻辑的方法
     *
     * @param canvas
     */
    protected void onRender(Canvas canvas) {
    }

    private class DrawThread extends Thread {

        /**
         * 每一帧刷新间隔时间,可改动
         */
        private static final long SLEEP_TIME = 33;

        private SurfaceHolder surfaceHolder;
        private boolean running = true;

        public DrawThread(SurfaceHolder holder) {
            super("RenderThread");
            surfaceHolder = holder;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (surfaceLock) {
                    if (!running) {
                        return;
                    }
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        render(canvas);  //这里做真正绘制的事情
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRun(boolean isRun) {
            this.running = isRun;
        }
    }
}