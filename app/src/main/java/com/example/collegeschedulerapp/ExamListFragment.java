package com.example.collegeschedulerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ExamListFragment extends Fragment {

    private final List<Exam> examsList = new ArrayList<>();

    public ExamListFragment() {
    }

    private ExamAdapter examAdapter;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exams, container, false);

        RecyclerView examsRecyclerView = view.findViewById(R.id.examsList);
        examsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        examAdapter = new ExamAdapter(examsList);
        examsRecyclerView.setAdapter(examAdapter);

        EditText editTextName = view.findViewById(R.id.editTextName);
        EditText editTextDate = view.findViewById(R.id.editTextDate);
        EditText editTextTime = view.findViewById(R.id.editTextTime);
        EditText editTextLocation = view.findViewById(R.id.editTextLocation);
        Button buttonAddExam = view.findViewById(R.id.buttonAddExam);

        buttonAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();
                String location = editTextLocation.getText().toString();
                if (!date.isEmpty() && !time.isEmpty() && !location.isEmpty()) {
                    Exam newExam = new Exam(name, date, time, location);
                    examsList.add(newExam);
                    examAdapter.notifyDataSetChanged();
                    editTextName.setText("");
                    editTextDate.setText("");
                    editTextTime.setText("");
                    editTextLocation.setText("");
                } else {
                    Toast.makeText(getContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
