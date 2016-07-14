package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kevin_Tian on 16/4/12.
 */
public class ProgressView extends View {
    private float delta = 10f;
    private Paint paint;
    float startX = 0;
    float hspace;
    float vspace;
    int mColor;

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        hspace = array.getDimension(R.styleable.ProgressView_progress_view_hspace, 0);
        vspace = array.getDimension(R.styleable.ProgressView_progress_view_vspace, 0);
        mColor = array.getColor(R.styleable.ProgressView_progress_view_color, Color.RED);
        array.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        paint.setStrokeWidth(vspace);
    }

    int index = 0;
    float space = 20;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getMeasuredWidth();
        if (startX>=width+hspace+space-(width%(hspace+space))){
            startX=0;
        }else {
            startX+=delta;
        }
        float start=startX;
        while (start<width){
            canvas.drawLine(start,5,start+hspace,5,paint);
            start+=(hspace+space);
        }
        start=startX-space-hspace;
        while (start>=-hspace){
            canvas.drawLine(start,5,start+hspace,5,paint);
            start-=(hspace+space);
        }

        invalidate();
    }
}
