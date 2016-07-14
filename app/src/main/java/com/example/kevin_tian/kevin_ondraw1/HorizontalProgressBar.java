package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kevin_Tian on 16/4/19.
 */
public class HorizontalProgressBar extends View {
    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    Paint mPaint;
    Context context;
    DrawFilter mDrawFilter;
    int mWidth, mHeight;
    int mDefaultColor;
    int mBackGrountColor;
    int mForegrounColor;
    boolean isAnimatorMode;
    int mMax;
    int mCurrentProgress;
    int mProgress;

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.context = context;
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mDefaultColor = getResources().getColor(android.R.color.holo_blue_bright);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        try {
            isAnimatorMode = array.getBoolean(R.styleable.HorizontalProgressBar_openAnimation, false);
            mForegrounColor = array.getColor(R.styleable.HorizontalProgressBar_foregroundColor, mDefaultColor);
            mBackGrountColor = array.getColor(R.styleable.HorizontalProgressBar_backgroundColor, mDefaultColor);
            mProgress = array.getInteger(R.styleable.HorizontalProgressBar_progress, 0);
            mMax = array.getInteger(R.styleable.HorizontalProgressBar_max, 100);
        } finally {
            array.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        if (widthSpecMode==MeasureSpec.EXACTLY||heightSpecMode==MeasureSpec.AT_MOST){
            mWidth=width;
        }else {
            mWidth=0;
        }

        if (heightSpecMode==MeasureSpec.AT_MOST||heightSpecMode==MeasureSpec.UNSPECIFIED){
            mHeight=Util.dpToPx(context.getResources(),6);
        }else {
            mHeight=height;
        }

        setMeasuredDimension(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(mDrawFilter);
        drawBackGround(canvas);
        if (isAnimatorMode){
            mCurrentProgress++;
            if (mCurrentProgress<=mProgress){
                drawTrack(canvas,mCurrentProgress);
                postInvalidate();
            }else {
                drawTrack(canvas,mProgress);
            }
        }else {
            drawTrack(canvas,mProgress);
        }
    }

    private void drawTrack(Canvas canvas, int mCurrentProgress) {
        int round=mHeight/2-1;
        float section = (float)mCurrentProgress / (float)mMax;
        RectF rectF=new RectF(2,2,(mWidth-2)*section,mHeight-2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mForegrounColor);
        canvas.drawRoundRect(rectF,round,round,mPaint);
    }

    private void drawBackGround(Canvas canvas) {
        int round=mHeight/2;
        mPaint.setColor(mBackGrountColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Util.dpToPx(context.getResources(),0.5f));
        RectF rectF=new RectF(1,1,mWidth-1,mHeight-1);
        canvas.drawRoundRect(rectF,round,round,mPaint);
    }

    public void setMax(int mMax) {
        this.mMax = mMax;
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress>mMax?mMax:mProgress;
        mCurrentProgress=0;
        invalidate();
    }
}
