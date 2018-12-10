package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

/**
 * @author sgx
 * 几何变换的使用大概分为三类：
 *
 * 使用 Canvas 来做常见的二维变换；
 * 使用 Matrix 来做常见和不常见的二维变换；
 * 使用 Camera 来做三维变换。
 *
 * 把 Matrix 应用到 Canvas 有两个方法： Canvas.setMatrix(matrix) 和 Canvas.concat(matrix)。
 *
 * Canvas.setMatrix(matrix)：用 Matrix 直接替换 Canvas 当前的变换矩阵，
 * 即抛弃 Canvas 当前的变换，改用 Matrix 的变换（注：根据下面评论里以及我在微信公众号中收到的反馈，
 * 不同的系统中 setMatrix(matrix) 的行为可能不一致，所以还是尽量用 concat(matrix) 吧）；
 * Canvas.concat(matrix)：用 Canvas 当前的变换矩阵和 Matrix 相乘，即基于 Canvas 当前的变换，
 * 叠加上 Matrix 中的变换。
 */
public class Practice07MatrixTranslateView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice07MatrixTranslateView(Context context) {
        super(context);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07MatrixTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        translate1(canvas);

//        translate2(canvas);

    }

    /**
     * 移动
     * @param canvas
     * matrix变换，在进行叠加操作说明：
     *  1.如果多次叠加变换都使用set开头的方法，那么第一次的set效果会被清除
     *  2.pre开头方法，意思为矩阵前乘
     *  3.post开头方法，意思是矩阵后乘
     *    因为矩阵的前乘与后乘是不一样的，所以在做效果变换的时候一定要注意前乘与后乘的区别，不然可能达不到效果
     */
    private void translate1(Canvas canvas) {
        //matrix ，canvas的平移操作对象是都是原点，产生的效果是一致的
        Matrix matrix = new Matrix();
        canvas.save();
        matrix.reset();
        matrix.setTranslate(-200,100);
//        canvas.setMatrix(matrix);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        matrix.reset();
        matrix.setTranslate(100,100);
        matrix.setRotate(45,bitmap.getWidth()/2 + point2.x,bitmap.getHeight()/2 + point2.y);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }

    private void translate2(Canvas canvas) {
        canvas.save();
        Matrix matrix = new Matrix();
        matrix.preTranslate(200,100);
//        matrix.postTranslate(200,100);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap,point1.x,point1.y,paint);
//        matrix.preTranslate(-200,100);
    }
}
