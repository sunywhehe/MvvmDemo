package com.jidouauto.mvvm.util.system;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

/**
 * 振动器
 *
 * @author yuyh.
 * @date 16/4/9.
 */
public class VibrateUtils {

    /**
     * 控制手机振动的毫秒数
     *
     * @param context
     * @param milliseconds
     */
    public static void vibrate(Context context, long milliseconds, int amplitude) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            vibrator.vibrate(milliseconds);
        } else {
            // 创建一次性振动
            // milliseconds 震动时长（ms）
            // amplitude 振动强度。这必须是1到255之间的值，或者DEFAULT_AMPLITUDE
            VibrationEffect vibrationEffect = VibrationEffect.createOneShot(milliseconds, amplitude);
            vibrator.vibrate(vibrationEffect);
        }
    }

    /**
     * 指定手机以pattern模式振动
     *
     * @param context
     * @param pattern new long[]{400,800,1200,1600}，就是指定在400ms、800ms、1200ms、1600ms这些时间点交替启动、关闭手机振动器
     * @param repeat  指定pattern数组的索引，指定pattern数组中从repeat索引开始的振动进行循环。-1表示只振动一次，非-1表示从 pattern的指定下标开始重复振动。
     */
    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            vibrator.vibrate(pattern, repeat);
        } else {
            // 创建波形振动
            // timings 交替开关定时的模式，从关闭开始。0的定时值将导致定时/振幅对被忽略。
            //repeat 振动重复的模式，如果您不想重复，则将索引放入计时数组中重复，或者-1。
            // -1 为不重复
            // 0 为一直重复振动
            // 1 则是指从数组中下标为1的地方开始重复振动，重复振动之后结束
            // 2 从数组中下标为2的地方开始重复振动，重复振动之后结束
            VibrationEffect vibrationEffect = VibrationEffect.createWaveform(pattern, repeat);
            vibrator.vibrate(vibrationEffect);
        }
    }

    public static void cancel(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.cancel();
    }

}
