package com.example.collegeschedulerapp.ui.toDo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.collegeschedulerapp.R;
import com.example.collegeschedulerapp.databinding.FragmentHomeBinding;

public class ActivityToFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel toDoViewModel = new ToDoViewModel(this);
        View view = inflater.inflate(R.layout.activity_to, container, false);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
