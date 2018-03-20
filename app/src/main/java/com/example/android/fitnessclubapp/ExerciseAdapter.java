package com.example.android.fitnessclubapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jashshah on 20/03/18.
 */

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder>{

    private List<Exercise> list;
    private Context mContext;

    public ExerciseAdapter(List<Exercise> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExerciseHolder(LayoutInflater.from(mContext).inflate(R.layout.exercise_recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ExerciseHolder holder, int position) {
        Exercise exercise = list.get(position);
        holder.image.setBackground(mContext.getDrawable(exercise.getDrawableId()));
        holder.name.setText(exercise.getName());
        holder.type.setText(exercise.getType());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ExerciseHolder extends RecyclerView.ViewHolder {

        TextView image;
        TextView name;
        TextView type;
        Button Start;

        public ExerciseHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.exercise_recycler_resource);
            name = itemView.findViewById(R.id.exercise_recycler_name);
            type = itemView.findViewById(R.id.exercise_recycler_type);
            Start = itemView.findViewById(R.id.exercise_recycler_start);
            Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Exercise exercise = list.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("name",exercise.getName());
                    bundle.putStringArray("benefits",exercise.getBenefits());
                    bundle.putStringArray("steps",exercise.getSteps());
                    bundle.putInt("resource",exercise.getDrawableId());
                    bundle.putString("key",exercise.getYoutubeId());
                    mContext.startActivity(new Intent(mContext,ExerciseView.class).putExtra("exercise",bundle));
                }
            });
        }
    }


}
