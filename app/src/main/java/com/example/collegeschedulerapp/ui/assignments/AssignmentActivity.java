package com.example.collegeschedulerapp.ui.assignments;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeschedulerapp.ClassViewActivity;
import com.example.collegeschedulerapp.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AssignmentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AssignmentAdapter assignmentAdapter;
    private List<AssignmentItem> assignmentItems = new ArrayList<>();
    private void sortAssignmentList() {
        Collections.sort(assignmentItems, new Comparator<AssignmentItem>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(AssignmentItem o1, AssignmentItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        assignmentAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        recyclerView = findViewById(R.id.recyclerView);
        assignmentAdapter = new AssignmentAdapter(assignmentItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(assignmentAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddClassDialog();
            }
        });

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_assignment, null);
        builder.setView(dialogView);

        final EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        final EditText classNameEditText = dialogView.findViewById(R.id.classNameEditText);
        final DatePicker datePicker = dialogView.findViewById(R.id.datePicker);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int position) {
                String title = titleEditText.getText().toString();
                String className = classNameEditText.getText().toString();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                LocalDate localDate = LocalDate.of(year, month, day);
                if(!title.isEmpty() && !className.isEmpty()) {
                    AssignmentItem newAssignment = new AssignmentItem(title, localDate, className);
                    assignmentItems.add(newAssignment);
                    sortAssignmentList();
                } else {
                    Toast.makeText(AssignmentActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.create().show();
    }

    void deleteAssignment(int position) {
        assignmentItems.remove(position);
        assignmentAdapter.notifyDataSetChanged();
    }
    void editAssignment(int postion) {
        AssignmentItem assignmentToEdit = assignmentItems.get(postion);
        showEditAssignmentDialog(assignmentToEdit);
    }
    private void showEditAssignmentDialog(AssignmentItem assignment) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_assignment, null);
        builder.setView(dialogView);

        final EditText editTitleEditText = dialogView.findViewById(R.id.editTitleEditText);
        final EditText editClassNameEditText = dialogView.findViewById(R.id.editClassNameEditText);
        final DatePicker editDatePicker = dialogView.findViewById(R.id.editDatePicker);

        editTitleEditText.setText(assignment.getTitle());
        editClassNameEditText.setText(assignment.getClassName());
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int position) {
                String title = editTitleEditText.getText().toString();
                String className = editClassNameEditText.getText().toString();
                int day = editDatePicker.getDayOfMonth();
                int month = editDatePicker.getMonth() + 1;
                int year = editDatePicker.getYear();
                LocalDate localDate = LocalDate.of(year, month, day);
                assignment.setTitle(title);
                assignment.setClassName(className);
                assignment.setDate(localDate);
                sortAssignmentList();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }
}
