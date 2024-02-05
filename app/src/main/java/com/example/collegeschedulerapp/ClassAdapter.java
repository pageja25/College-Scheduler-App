package com.example.collegeschedulerapp;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<ClassItem> {
    public ClassAdapter(@NonNull Context context, List<ClassItem> classes) {
        super(context, 0, classes);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClassItem classItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }
        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);
        String classTitle = classItem.getName() + " " + CalendarUtils.formattedTime(classItem.getTime());
        eventCellTV.setText(classTitle);
        return convertView;
    }
}
