package com.example.kevin_tian.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.kevin_tian.kevin_ondraw1.R;

import java.util.List;

/**
 * Created by Kevin_Tian on 16/7/27.
 */
public class TextScrollView extends ScrollView{

    List<String> strings;
    LinearLayout linearLayout;
    LayoutInflater inflater;
    int width;
    int height;

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public TextScrollView(Context context) {
        this(context,null);
    }

    public TextScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        init(context);
    }

    private void init(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void addItem(String text){
        removeAllViews();
        View view = inflater.inflate(R.layout.layout,null);
        LinearLayout layout = (LinearLayout) view;
        TextView textView = (TextView) layout.findViewById(R.id.myText);
        textView.setText(text);
        linearLayout.addView(layout);
        addView(linearLayout);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            View child = getChildAt(i);
            width+=child.getMeasuredWidth();
            if (height<child.getMeasuredHeight()){
                height = child.getMeasuredHeight();
            }
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
