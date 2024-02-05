package com.example.collegeschedulerapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private EditText classTitle;
    private EditText instructorName;
    private boolean[] checkedDays = new boolean[7];

    private EditText desc;

    private EditText location;

    private LocalTime time;

    Button timeButton;
    int hour, minute;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initWidgets();


    }
    private void initWidgets() {
        timeButton = findViewById(R.id.timePickerButton);
        classTitle = findViewById(R.id.class_title);
        location = findViewById(R.id.location);
        instructorName = findViewById(R.id.instructor_name);
        desc = findViewById(R.id.descript);
        checkedDays[0] = ((CheckBox)findViewById(R.id.sunday_checked)).isChecked();
        checkedDays[1] = ((CheckBox)findViewById(R.id.monday_checked)).isChecked();
        checkedDays[2] = ((CheckBox)findViewById(R.id.tuesday_checked)).isChecked();
        checkedDays[3] = ((CheckBox)findViewById(R.id.wednesday_checked)).isChecked();
        checkedDays[4] = ((CheckBox)findViewById(R.id.thursday_checked)).isChecked();
        checkedDays[5] = ((CheckBox)findViewById(R.id.friday_checked)).isChecked();
        checkedDays[6] = ((CheckBox)findViewById(R.id.saturday_checked)).isChecked();
    }

    public void saveClassAction(View view) {
        time = LocalTime.parse(timeButton.getText());
        String className = classTitle.getText().toString();
        String instructor = instructorName.getText().toString();
        String location = this.location.getText().toString();
        String description = desc.getText().toString();
        ClassItem newClass = new ClassItem(className, instructor, location, time, checkedDays, description);
        ClassItem.classes.add(newClass);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("SelectTime");
        timePickerDialog.show();
    }
}
