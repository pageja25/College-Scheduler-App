package com.example.collegeschedulerapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    private List<ClassModel> classModelList;

    public ClassAdapter(List<ClassModel> classModelList) {
        this.classModelList = classModelList;
    }
    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        ClassModel currentClass = classModelList.get(position);
        holder.dayTextView.setText(currentClass.getDay());
        holder.classNameTextView.setText(currentClass.getClassName());
        holder.timeTextView.setText(currentClass.getTime().toString());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    ((ClassViewActivity) view.getContext()).deleteClass(adapterPosition);
                }
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Call a method to handle editing
                    ((ClassViewActivity) view.getContext()).editClass(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return classModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView classNameTextView;
        public TextView timeTextView;
        public TextView dayTextView;
        public ImageButton deleteButton;
        public ImageButton editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classNameTextView = itemView.findViewById(R.id.classNameTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}
