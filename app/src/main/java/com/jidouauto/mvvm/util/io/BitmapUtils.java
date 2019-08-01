package com.jidouauto.mvvm.util.io;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import com.jidouauto.mvvm.util.StringUtils;
import com.jidouauto.mvvm.util.data.safe.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author yuyh.
 * @date 16/4/9.
 */
public class BitmapUtils {

    private static final String TAG = BitmapUtils.class.getSimpleName();

    /**
     * Bitmap 转 Bytes
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * Bytes 转 Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * Bitmap转换成Base64编码的String
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), Base64.DEFAULT);
    }

    /**
     * string2Bitmap
     *
     * @param str
     * @return
     */
    public static Bitmap stringToBitmap(String str) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        byte[] bitmapArray = Base64.decode(str, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
    }

    /**
     * Drawable 转 Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * BitMap 转 Drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Resources resources, Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(resources, bitmap);
    }

    /**
     * 获取View的截图
     *
     * @param view
     * @return
     */
    public static Bitmap viewToBitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    /**
     * 图片缩放
     *
     * @param org       原始图片
     * @param newWidth  缩放后的宽度
     * @param newHeight 缩放后的高
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * 图片缩放
     *
     * @param org         原始图片
     * @param scaleWidth  宽度缩放倍数
     * @param scaleHeight 高度缩放倍数
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * 获取圆形图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 生成缩略图
     *
     * @param bitMap
     * @param needRecycle 原图是否销毁
     * @param newHeight
     * @param newWidth
     * @return
     */
    public static Bitmap getBitmapThumbnail(Bitmap bitMap, boolean needRecycle, int newHeight, int newWidth) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        if (needRecycle) {
            bitMap.recycle();
        }
        return newBitMap;
    }

    /**
     * 保存BitMap成文件
     *
     * @param bitmap
     * @param file
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, File file) {
        if (bitmap == null) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 保存Bitmap到文件
     *
     * @param bitmap
     * @param absPath 文件绝对路径
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String absPath) {
        return saveBitmap(bitmap, new File(absPath));
    }

    /**
     * 反射 得到本地视频的预览图
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap createVideoThumbnail(Context context, Uri uri) {
        Bitmap bitmap = null;
        String className = "android.media.MediaMetadataRetriever";
        Object objectMediaMetadataRetriever = null;
        Method release = null;
        try {
            objectMediaMetadataRetriever = Class.forName(className).newInstance();
            Method setDataSourceMethod = Class.forName(className).getMethod("setDataSource", Context.class, Uri.class);
            setDataSourceMethod.invoke(objectMediaMetadataRetriever, context, uri);
            Method getFrameAtTimeMethod = Class.forName(className).getMethod("getFrameAtTime");
            bitmap = (Bitmap) getFrameAtTimeMethod.invoke(objectMediaMetadataRetriever);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (release != null) {
                    release.invoke(objectMediaMetadataRetriever);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    public static Intent buildImageGetIntent(Uri saveTo, int outputx, int outputy, boolean returnData) {
        return buildImageGetIntent(saveTo, 1, 1, outputx, outputy, returnData);
    }

    public static Intent buildImageGetIntent(Uri saveTo, int aspectx, int aspecty,
                                             int outputx, int outputy, boolean returnData) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        intent.putExtra("output", saveTo);
        intent.putExtra("aspectX", aspectx);
        intent.putExtra("aspectY", aspecty);
        intent.putExtra("outputx", outputx);
        intent.putExtra("outputy", outputy);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int outputx, int outputy, boolean returnData) {
        return buildImageCropIntent(uriFrom, uriTo, 1, 1, outputx, outputy, returnData);
    }

    public static Intent buildImageCropIntent(Uri uriFrom, Uri uriTo, int aspectx, int aspecty,
                                              int outputx, int outputy, boolean returnData) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uriFrom, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uriTo);
        intent.putExtra("aspectX", aspectx);
        intent.putExtra("aspectY", aspecty);
        intent.putExtra("outputX", outputx);
        intent.putExtra("outputY", outputy);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImageCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int h = options.outHeight;
        int w = options.outWidth;
        int inSampleSize = 0;
        if (h > reqHeight || w > reqWidth) {
            float ratiow = (float) w / reqWidth;
            float ratioh = (float) h / reqHeight;
            inSampleSize = (int) Math.min(ratioh, ratiow);
        }
        inSampleSize = Math.max(1, inSampleSize);
        return inSampleSize;
    }

    public static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public byte[] compressBitmapToBytes(String filePath, int reqWidth, int reqHeight, int quality) {
        Bitmap bitmap = getSmallBitmap(filePath, reqWidth, reqHeight);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        bitmap.recycle();
        return bytes;
    }

    public byte[] compressBitmapSmallTo(String filePath, int reqWidth, int reqHeight, int maxLenth) {
        int quality = 100;
        byte[] bytes = compressBitmapToBytes(filePath, reqWidth, reqHeight, quality);
        while (bytes.length > maxLenth && quality > 0) {
            quality = quality / 2;
            bytes = compressBitmapToBytes(filePath, reqWidth, reqHeight, quality);
        }
        return bytes;
    }

    public byte[] compressBitmapQuikly(String filePath) {
        return compressBitmapToBytes(filePath, 480, 800, 50);
    }

    public byte[] compressBitmapQuiklySmallTo(String filePath, int maxLenth) {
        return compressBitmapSmallTo(filePath, 480, 800, maxLenth);
    }
}
