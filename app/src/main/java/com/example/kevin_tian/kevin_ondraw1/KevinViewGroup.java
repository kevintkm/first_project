package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Kevin_Tian on 16/4/18.
 */
public class KevinViewGroup extends LinearLayout {
    Context context;
    public KevinViewGroup(Context context) {
        this(context,null);
    }

    public KevinViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KevinViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        TextView textView=new TextView(context);
        textView.setText("This is First");
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,Util.dpToPx(context.getResources(),140));
        params.rightMargin=Util.dpToPx(context.getResources(),40);
        textView.setTranslationX(-50);
        textView.setLayoutParams(params);
        this.addView(textView);
        View view= LayoutInflater.from(context).inflate(R.layout.layout,null,false);
        view.setTranslationY(100);
        this.addView(view);

    }


}
