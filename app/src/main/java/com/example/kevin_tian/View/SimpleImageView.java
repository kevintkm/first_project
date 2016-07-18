package com.example.kevin_tian.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.kevin_tian.kevin_ondraw1.R;

import org.w3c.dom.Attr;

/**
 * Created by Kevin_Tian on 16/7/6.
 */
public class SimpleImageView extends View{

    public Context context;

    private Paint mPaint;
    private Drawable mDrable;

    private int mWidth;
    
    private int mHeight;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public SimpleImageView(Context context) {
        super(context);
        initAttrs(context,null);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #View(Context, AttributeSet, int)
     */
    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        TypedArray array=null;
        try {
            array = context.obtainStyledAttributes(attributeSet, R.styleable.SimepleImageView);
            mDrable = array.getDrawable(R.styleable.SimepleImageView_src);
        } finally {
            if (array!=null)
                array.recycle();
        }
        if (mDrable==null)
            return;
        mWidth = mDrable.getIntrinsicWidth();
        mHeight = mDrable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measureValue(widthMode,width),measureValue(heightMode,height));
    }

    private int measureValue(int widthMode, int width) {
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                return width;
            case MeasureSpec.UNSPECIFIED:
                case MeasureSpec.AT_MOST:
                    break;

        }
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrable == null){
            return;
        }
        BitmapDrawable drawable = (BitmapDrawable)mDrable;
        canvas.drawBitmap(drawable.getBitmap(),getLeft(),getTop(),mPaint);
    }
}
