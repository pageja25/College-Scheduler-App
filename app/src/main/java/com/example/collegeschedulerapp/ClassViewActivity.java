package com.example.collegeschedulerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;
    private List<ClassModel> classModelList = new ArrayList<>();
    private void sortClassModelList() {
        Collections.sort(classModelList, new Comparator<ClassModel>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(ClassModel class1, ClassModel class2) {
                // Assuming dayOfWeek is a string like "Monday", "Tuesday", etc.
                return class1.getTime().compareTo(class2.getTime());
            }
        });

        classAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);

        recyclerView = findViewById(R.id.recyclerView);
        classAdapter = new ClassAdapter(classModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(classAdapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        View dialogView = inflater.inflate(R.layout.dialog_add_class, null);
        builder.setView(dialogView);

        final EditText classNameEditText = dialogView.findViewById(R.id.classNameEditText);
        final EditText sectionEditText = dialogView.findViewById(R.id.sectionEditText);
        final EditText dayEditText = dialogView.findViewById(R.id.dayEditText);
        final TimePicker timePicker = dialogView.findViewById(R.id.timePicker);
        final EditText locationEditText = dialogView.findViewById(R.id.locationEditText);
        final EditText descEditText = dialogView.findViewById(R.id.descEditText);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String className = classNameEditText.getText().toString();
                String section = sectionEditText.getText().toString();
                String day = dayEditText.getText().toString();
                String location = locationEditText.getText().toString();
                String desc = descEditText.getText().toString();

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                LocalTime localTime = LocalTime.of(hour, minute);

                if (!className.isEmpty() && !day.isEmpty() && !location.isEmpty() && !desc.isEmpty()) {
                    ClassModel newClass = new ClassModel(className, section, localTime, day, location, desc);
                    classModelList.add(newClass);
                    sortClassModelList();
                } else {
                    Toast.makeText(ClassViewActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
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
    void deleteClass(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Class");
        builder.setMessage("Are you sure you want to delete this class?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                classModelList.remove(position);
                classAdapter.notifyItemRemoved(position);
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    void editClass(int position) {
        // Retrieve the class at the specified position
        ClassModel classToEdit = classModelList.get(position);

        // Launch an edit dialog or activity
        // For simplicity, I'll show a Toast message with the current class details
        showEditClassDialog(classToEdit);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showEditClassDialog(ClassModel classToEdit) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_class, null);
        builder.setView(dialogView);

        final EditText classNameEditText = dialogView.findViewById(R.id.editClassNameEditText);
        final EditText dayEditText = dialogView.findViewById(R.id.editDayEditText);
        final TimePicker timePicker = dialogView.findViewById(R.id.editTimePicker);

        // Pre-fill the dialog with existing class details
        classNameEditText.setText(classToEdit.getClassName());
        dayEditText.setText(classToEdit.getDay());
        timePicker.setHour(classToEdit.getTime().getHour());
        timePicker.setMinute(classToEdit.getTime().getMinute());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                // Update the class details
                String newClassName = classNameEditText.getText().toString();
                String newDay = dayEditText.getText().toString();
                int newHour = timePicker.getHour();
                int newMinute = timePicker.getMinute();

                // Update the class in the list
                classToEdit.setClassName(newClassName);
                classToEdit.setDay(newDay);
                classToEdit.setTime(LocalTime.of(newHour, newMinute));

                // Notify the adapter of the change
                sortClassModelList();
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
