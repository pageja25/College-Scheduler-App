package com.example.collegeschedulerapp;

import java.time.LocalTime;

public class ClassModel {
    private String className;
    private String section;
    private LocalTime time;
    private String day;
    private String location;
    private String desc;
    public ClassModel(String className, String section, LocalTime time, String day, String location, String desc) {
        this.className = className;
        this.section = section;
        this.time = time;
        this.day = day;
        this.location = location;
        this.desc = desc;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
