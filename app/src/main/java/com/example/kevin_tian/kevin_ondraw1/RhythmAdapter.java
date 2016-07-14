package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2014/12/4.
 */
public class RhythmAdapter extends BaseAdapter {

    Context context;
    List<String> lists;
    public RhythmAdapter(Context context) {
        super();
        this.context=context;
        lists=new ArrayList<String>();
    }

    private void setDataAndNotifyChanged(List<String> list){
        lists.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }
}
