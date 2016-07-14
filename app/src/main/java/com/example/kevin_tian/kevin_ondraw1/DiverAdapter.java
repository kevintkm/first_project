package com.example.kevin_tian.kevin_ondraw1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kevin_Tian on 16/5/10.
 */
public class DiverAdapter extends RecyclerView.Adapter<DiverAdapter.ViewHolder> {

    Context context;

    List<String> lists;

    public void setLists(List<String> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public DiverAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public DiverAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DiverAdapter.ViewHolder holder, int position) {
        holder.textView.setText(lists.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.myText);
        }
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return lists != null ? lists.size() : 0;
    }
}
