package com.example.collegeschedulerapp.ui.toDo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToDoViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public ToDoViewModel(ActivityToFragment activityToFragment) {
        mText = new MutableLiveData<>();
        mText.setValue("Assignments to-do list");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
