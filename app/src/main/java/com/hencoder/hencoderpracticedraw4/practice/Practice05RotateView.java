package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice05RotateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice05RotateView(Context context) {
        super(context);
    }

    public Practice05RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        //一个参数的旋转方法，参照点为原点,图片的绘制坐标为图片的左上角，而文字（drawText的绘制坐标为左下角在往左一点）
//        canvas.rotate(90f);
        canvas.rotate(120f,point1.x + bitmap.getWidth()/2,point1.y + bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        //参数点为px,py
        canvas.rotate(45f,point2.x + 50,point2.y + 50);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}