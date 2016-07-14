package com.example.kevin_tian.kevin_ondraw1;

/**
 * Created by think on 2014/12/4.
 */

public abstract interface RhythmListener
{
    public abstract void onRhythmItemChanged(int paramInt);

    public abstract void onSelected(int paramInt);

    public abstract void onStartSwipe();

    public abstract void onViewpagerChanged(int position);
}
