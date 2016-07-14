package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Kevin_Tian on 16/5/11.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    Drawable mDivider;

    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn before the item views are drawn,
     * and will thus appear underneath the views.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    public DividerItemDecoration(Context context) {
        final TypedArray array=context.obtainStyledAttributes(ATTRS);
        mDivider= array.getDrawable(0);
        array.recycle();
    }

    /**
     * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
     * Any content drawn by this method will be drawn after the item views are drawn
     * and will thus appear over the views.
     *
     * @param c      Canvas to draw into
     * @param parent RecyclerView this ItemDecoration is drawing into
     * @param state  The current state of RecyclerView.
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left=parent.getPaddingLeft();
        final int right=parent.getWidth()-parent.getPaddingRight();
        final int count=parent.getChildCount();
        Log.d("RecyclerView item Count",count+"");
        for (int i=0;i<count;i++){
            final View child=parent.getChildAt(i);
            RecyclerView v=new RecyclerView(parent.getContext());
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top=child.getBottom()+params.bottomMargin;
            final int bottom=top+13;
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     * <p/>
     * <p/>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     * <p/>
     * <p/>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,13);
    }
}
