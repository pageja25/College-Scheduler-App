package com.example.collegeschedulerapp.ui.assignments;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeschedulerapp.R;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder>{
    private List<AssignmentItem> assignments;

    public AssignmentAdapter(List<AssignmentItem> assignmentItems) {
        this.assignments = assignmentItems;
    }

    @NonNull
    @Override
    public AssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.ViewHolder holder, int position) {
        AssignmentItem assignment = assignments.get(position);
        holder.titleTextView.setText(assignment.getTitle());
        holder.classNameTextView.setText(assignment.getClassName());
        holder.dueDateTextView.setText(assignment.getDate().toString());
        holder.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    ((AssignmentActivity) view.getContext()).deleteAssignment(adapterPosition);
                }
            }
        });
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    ((AssignmentActivity) view.getContext()).editAssignment(adapterPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView classNameTextView;
        public TextView dueDateTextView;
        public ImageButton completeButton;
        public ImageButton editButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            classNameTextView = itemView.findViewById(R.id.classNameTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            completeButton = itemView.findViewById(R.id.completeButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }

}
