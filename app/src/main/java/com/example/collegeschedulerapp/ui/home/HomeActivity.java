package com.example.collegeschedulerapp.ui.home;

import static com.example.collegeschedulerapp.CalendarUtils.daysInMonthArray;
import static com.example.collegeschedulerapp.CalendarUtils.daysInWeekArray;
import static com.example.collegeschedulerapp.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeschedulerapp.AddActivity;
import com.example.collegeschedulerapp.CalendarAdapter;
import com.example.collegeschedulerapp.CalendarUtils;
import com.example.collegeschedulerapp.ClassAdapter;
import com.example.collegeschedulerapp.ClassItem;
import com.example.collegeschedulerapp.R;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView classListView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        CalendarUtils.selectedDate = LocalDate.now();
        initWidgets();

        setWeekView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        classListView = findViewById(R.id.classListView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setClassAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void newClassAction(View view) {
        startActivity(new Intent(this, AddActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onResume() {
        super.onResume();
        setClassAdapter();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setClassAdapter() {
        ArrayList<ClassItem> dailyClasses = ClassItem.classesForDay(CalendarUtils.selectedDate.getDayOfWeek().getValue()  - 1);
        ClassAdapter classAdapter = new ClassAdapter(getApplicationContext(), dailyClasses);
        classListView.setAdapter(classAdapter);
    }
}
