package com.dasong.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.dasong.base.context.App;

public class BitmapUtils {

    /**
     * 高斯模糊，Android 4.2+有效
     * @param source
     * @param radius 0-25,0不模糊，25最模糊
     * @return
     */
    public static Bitmap rsBlur(Bitmap source,int radius){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1){
            throw new RuntimeException("此方法只能在Android4.2+使用");
        }
        if(radius <= 0 || radius > 25){
            throw new RuntimeException("radius必须在[0-25)之间");
        }

        Bitmap inputBmp = source;
        //(1)
        RenderScript renderScript =  RenderScript.create(App.getApplication());

        LogUtils.i("scale size:"+inputBmp.getWidth()+"*"+inputBmp.getHeight());

        // Allocate memory for Renderscript to work with
        //(2)
        final Allocation input = Allocation.createFromBitmap(renderScript,inputBmp);
        final Allocation output = Allocation.createTyped(renderScript,input.getType());
        //(3)
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        //(4)
        scriptIntrinsicBlur.setInput(input);
        //(5)
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        //(6)
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        //(7)
        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);
        //(8)
        renderScript.destroy();

        return inputBmp;
    }

    /**
     * 生成透明背景的圆形图片,注意要生成透明背景的圆形，图片一定要png类型的，不能是jpg类型
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toCircle(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(circleBitmap);
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight()));
            float roundPx = 0.0f;
            // 以较短的边为标准
            if (bitmap.getWidth() > bitmap.getHeight()) {
                roundPx = bitmap.getHeight() / 2.0f;
            } else {
                roundPx = bitmap.getWidth() / 2.0f;
            }
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap, src, rect, paint);
            return circleBitmap;
        } catch (Exception e) {
            return bitmap;
        }
    }

    public static Bitmap fit(String path,int hopeWidth,int hopeHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        calculate(options,hopeWidth,hopeHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path,options);
    }

    public static Bitmap fit(int id,int hopeWidth,int hopeHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(App.getApplication().getResources(),id,options);
        calculate(options,hopeWidth,hopeHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(App.getApplication().getResources(),id,options);
    }

    private static void calculate(BitmapFactory.Options options,int hopeWidth,int hopeHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(height > hopeHeight || width > hopeHeight){
            final int h = height / 2;
            final int w = width / 2;
            while((h/inSampleSize) >= hopeHeight && (w/inSampleSize) >= hopeWidth){
                inSampleSize *= 2;
            }
        }
        options.inSampleSize = inSampleSize;
    }
}
