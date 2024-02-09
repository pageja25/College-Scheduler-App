package com.example.collegeschedulerapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private List<Exam> examsList;

    public ExamAdapter(List<Exam> examsList) {
        this.examsList = examsList;
    }
    @Override
    public int getItemCount() {
        return examsList.size();
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        return new ExamViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        Exam exam = examsList.get(position);
        holder.examName.setText(exam.getName());
        holder.examDate.setText(exam.getDate());
        holder.examTime.setText(exam.getTime());
        holder.examLocation.setText(exam.getLocation());
    }

    public static class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView examName;
        public TextView examDate;
        public TextView examTime;
        public TextView examLocation;

        public ExamViewHolder(View view) {
            super(view);
            examName = view.findViewById(R.id.examName);
            examDate = view.findViewById(R.id.examDate);
            examTime = view.findViewById(R.id.examTime);
            examLocation = view.findViewById(R.id.examLocation);
        }
    }
}