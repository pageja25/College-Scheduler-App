package com.example.collegeschedulerapp;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClassItem {
    public static ArrayList<ClassItem> classes = new ArrayList<>();
    public static ArrayList<ClassItem> classesForDay(int day) {
        ArrayList<ClassItem> classesForDay = new ArrayList<>();
        for (ClassItem classItem : classes) {
            if (classItem.daysChecked[day]) {
                classesForDay.add(classItem);
            }
        }
        return classesForDay;
    }
    private String name;
    private String instructor;
    private String location;
    private LocalTime time;

    boolean[] daysChecked;
    String desc;
    public ClassItem(String name, String instructor, String location, LocalTime time, boolean[] daysChecked, String desc) {
        this.name = name;
        this.instructor = instructor;
        this.daysChecked = daysChecked;
        this.location = location;
        this.time = time;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
