package com.example.kevin_tian.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kevin_Tian on 16/7/20.
 */
public class SimpleCircle extends View {

    public SimpleCircle(Context context) {
        this(context, null);
    }

    public SimpleCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint bigCirclePaint = new Paint();
        //消除锯齿
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(0xffff0000);
        //x,y 为圆心坐标 mRadius为半径
        canvas.drawCircle(100, 100, 100, bigCirclePaint);
        //饼状图
        Paint sectorPaint = new Paint();
        sectorPaint.setColor(0xff00ff00);
        sectorPaint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, 200, 200);
        //参数说明见知识补充
        for (int i=0;i<mPercent;i++){
            mCurPercent = i;
            postInvalidateDelayed(15);
        }
        canvas.drawArc(rect, 270, (float) 3.6 * mCurPercent, true, sectorPaint);
        Paint smallPaint = new Paint();
        smallPaint.setColor(0xffffffff);
        canvas.drawCircle(100, 100, 80, smallPaint);

        Paint textPaint = new Paint();
        textPaint.setColor(0xff0000ff);

        textPaint.setTextSize(48);
        String text = mCurPercent+"%";
        float lenth = textPaint.measureText(text);
        float height = textPaint.ascent() + textPaint.descent();
        canvas.drawText(text, 100 - lenth / 2, 100 - height / 2, textPaint);

    }

    int mPercent;
    int mCurPercent;

    public void setPercent(final int percent) {

        this.mPercent = percent;

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<percent;i++){
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    SimpleCircle.this.postInvalidate();
                }
            }

        }).start();*/


       /* for(int i =0;i<percent;i++){
            mCurPercent = i;
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.postInvalidate();
        }*/
    }
}
