package com.example.kevin_tian.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.kevin_tian.kevin_ondraw1.R;

/**
 * Created by kevin.tian on 2016/7/27.
 * 熟悉Matrix操作相关的图像变换,与圆弧相结合
 */
public class SimpleMatrix extends View {

    private Matrix matrix;
    private Paint arcPaint;
    private Paint picPaint;
    private Bitmap picBitmap;


    int width;
    int height;
    int radius;
    int strokeWidth;
    int currentPercent;
    int totalPercent;

    public SimpleMatrix(Context context) {
        this(context, null);
    }

    public SimpleMatrix(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        matrix = new Matrix();
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(Color.GRAY);
        arcPaint.setStyle(Paint.Style.STROKE);
        strokeWidth = 6;
        arcPaint.setStrokeWidth(strokeWidth);
        picPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        picBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.location1_03);
    }

    public void setTotalPercent(int totalPercent) {
        this.totalPercent = totalPercent;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        radius = width / 3;
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPercent <= totalPercent) {
            currentPercent++;
            canvas.save();
            int r = radius - strokeWidth / 2;
            canvas.translate(radius, radius);
            canvas.drawArc(new RectF(-r*3/4, -r*3/4, r*3/4, r*3/4), 270, (float) (3.6 * currentPercent), false, arcPaint);
            canvas.rotate(90 + (float) (3.6 * currentPercent));
            Matrix matrix = new Matrix();
            matrix.preTranslate(-r*3/4-picBitmap.getWidth()*3/8, -picBitmap.getHeight() / 2);
            canvas.drawBitmap(picBitmap, matrix, picPaint);
            canvas.restore();
            if (currentPercent < totalPercent)
                postInvalidate();
        }
    }
}
