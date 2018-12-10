package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

/**
 * @author sgx
 * 范围裁切
 */
public class Practice01ClipRectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice01ClipRectView(Context context) {
        super(context);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Clip(剪切)的时机：通常理解的clip(剪切)，是对已经存在的图形进行clip的。
        // 但是，在android上是对canvas（画布）上进行clip的，要在画图之前对canvas进行clip，
        // 如果画图之后再对canvas进行clip不会影响到已经画好的图形。一定要记住clip是针对canvas而非图形
        Path path = new Path();
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        path.addCircle(min / 2,min / 2,min / 2,Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap,0,0,paint);

//        int left = (getWidth() - bitmap.getWidth()) / 2;
//        int top = (getHeight() - bitmap.getHeight()) / 2;
//
//        canvas.save();
//        canvas.clipRect(left + 50,top + 50,left + 300,top + 300);
//
//        canvas.clipPath(path);
//        canvas.drawBitmap(bitmap, left, top, paint);
//        canvas.restore();
    }
}
