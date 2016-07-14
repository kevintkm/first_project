package com.example.kevin_tian.kevin_ondraw1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * created by Wang Huan
 * this is a custom view for like as zuimei UI
 */
public class Rhythmlayout extends LinearLayout {

    private Context context;
    private final static int NUM = 7;
    private float DEFULT_OBJECT_SHORT_HEIGHT;
    private float DEFULT_OBJECT_HIGH_HEIGHT;
    private final static int DEFAULT_SPRING_ANIMATION_VELOCITY = 1000;
    private final static int DEFAULT_HORIZONTAL_ANIMATION_DURING = 300;

    private final static int DEFULT_OBJECT_SHORT_NORMAL_HEIGHT_PX = 10;
    private final static int DEFULT_OBJECT_LONG_NORMAL_HEIGHT_PX = 70;
    private final static int DEFULT_OBJECT_LONG_HEIGHT_PX = 80;

    private RhythmAdapter adapter;
    private float object_width;
    private float object_height;
    private float screen_width;
    private int viewpager_current_position;
    private int viewpager_last_position;

    private List<String> list = new ArrayList<>();

    public Rhythmlayout(Context context) {
        super(context);
        init(context);
    }

    public Rhythmlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Rhythmlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Rhythmlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        screen_width = getDisplayMetrics().widthPixels;
        object_width = screen_width / NUM;
        DEFULT_OBJECT_SHORT_HEIGHT = Util.dpToPx(getResources(), DEFULT_OBJECT_SHORT_NORMAL_HEIGHT_PX);
        DEFULT_OBJECT_HIGH_HEIGHT = Util.dpToPx(getResources(), DEFULT_OBJECT_LONG_HEIGHT_PX);
        object_height = DEFULT_OBJECT_HIGH_HEIGHT;
        this.setGravity(Gravity.BOTTOM);
    }

    public void setAdapter(RhythmAdapter adapter) {
        this.adapter = adapter;
    }

    public RhythmListener getRhythmListener() {
        return rhythmListener;
    }

    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private List<View> getVisibleViews() {
        List<View> views = new ArrayList<>();
        if (adapter == null)
            return views;
        if (adapter.getCount() <= NUM) {
            for (int i = 0; i < adapter.getCount(); i++) {
                views.add(adapter.getView(i, null, null));
            }
            return views;
        }
        if (viewpager_current_position <= 4) {
            for (int i = 0; i < NUM; i++) {
                views.add(adapter.getView(i, null, null));
            }
            return views;
        }
        if (viewpager_current_position > 4) {
            for (int i = viewpager_current_position - 4; i < viewpager_current_position + 3; i++) {
                views.add(adapter.getView(i, null, null));
            }
            return views;
        }
        return views;
    }


    public void notifyViewDataSetChanged(List<String> list) {
        this.list.addAll(list);
        notifyViewDataSetChanged();
    }

    public void notifyViewDataSetChanged() {
        this.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            String rhythmObject = list.get(i);


            View view = LayoutInflater.from(context).inflate(R.layout.layout, null);
            TextView textView = (TextView) view.findViewById(R.id.myText);
            textView.setText(rhythmObject + i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textView.setLayoutParams(params);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) object_width, (int) object_height);
            layoutParams.rightMargin = Util.dpToPx(context.getResources(), 3);

            int translationY = 0;
            if (i == 0) {
                translationY = DEFULT_OBJECT_SHORT_NORMAL_HEIGHT_PX * 2;
                params.gravity = Gravity.CENTER;
            } else {
                translationY = DEFULT_OBJECT_LONG_NORMAL_HEIGHT_PX * 2;
                params.gravity = Gravity.TOP;
            }
            view.setLayoutParams(layoutParams);
            view.setTranslationY(translationY);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    rhythmListener.onViewpagerChanged(finalI);
                }
            });
            addView(view, i, layoutParams);

        }

    }

    private RhythmListener rhythmListener = new RhythmListener() {
        @Override
        public void onRhythmItemChanged(int paramInt) {

        }

        @Override
        public void onSelected(int paramInt) {

        }

        @Override
        public void onStartSwipe() {

        }

        @Override
        public void onViewpagerChanged(final int position) {
            viewpager_current_position = position;
            AnimatorSet animatorSet = new AnimatorSet();

            List<Animator> animators = new ArrayList<>();
            for (int i = 0; i < Rhythmlayout.this.getChildCount(); i++) {
                View viewObject = Rhythmlayout.this.getChildAt(i);
                if (viewpager_current_position != 0 &&
                        viewpager_current_position != 1 &&
                        viewpager_current_position != 2 &&
                        viewpager_current_position != 3) {
                    animators.add(ObjectAnimator.ofFloat(viewObject,
                            "translationX",
                            -viewObject.getMeasuredWidth() * (viewpager_last_position - 3),
                            -viewObject.getMeasuredWidth() * (position - 3)));
                }
                if (viewpager_last_position == 4 && viewpager_current_position == 3) {
                    animators.add(ObjectAnimator.ofFloat(viewObject,
                            "translationX",
                            -viewObject.getMeasuredWidth() * (viewpager_last_position - 3),
                            -viewObject.getMeasuredWidth() * (position - 3)));
                }
            }

            animatorSet.playTogether(animators);
            animatorSet.setDuration(DEFAULT_HORIZONTAL_ANIMATION_DURING);
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    invalidateHeight();
                    viewpager_last_position = position;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            animatorSet.start();
        }
    };

    public void invalidateHeight() {
        final View lastView = this.getChildAt(viewpager_last_position);
        lastView.setTranslationY(DEFULT_OBJECT_LONG_NORMAL_HEIGHT_PX  * 2);
        final View view = this.getChildAt(viewpager_current_position);
        view.setTranslationY(DEFULT_OBJECT_SHORT_NORMAL_HEIGHT_PX* 2);
    }

}