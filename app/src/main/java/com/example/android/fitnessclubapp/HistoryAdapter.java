package com.example.android.fitnessclubapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jashshah on 20/03/18.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>{
    private Context mContext;
    private List<HistoryObject> list;

    public HistoryAdapter(Context mContext, List<HistoryObject> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(mContext).inflate(R.layout.history_recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        HistoryObject object = list.get(position);
        holder.circle.setText(String.valueOf(position));
        holder.name.setText(object.getName());
        holder.time.setText(object.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder{

        public TextView circle;
        public TextView name;
        public TextView time;

        public HistoryHolder(View itemView) {
            super(itemView);
            circle = itemView.findViewById(R.id.history_recycler_circle);
            name = itemView.findViewById(R.id.history_recycler_name);
            time = itemView.findViewById(R.id.history_recycler_time);
        }
    }

}
