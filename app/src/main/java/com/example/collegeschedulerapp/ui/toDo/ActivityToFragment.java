package com.example.collegeschedulerapp.ui.toDo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.collegeschedulerapp.R;
import com.example.collegeschedulerapp.databinding.FragmentHomeBinding;

public class ActivityToFragment extends Fragment {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel toDoViewModel = new ToDoViewModel(this);
        View view = inflater.inflate(R.layout.activity_to, container, false);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        toDoViewModel.getText().observe(getViewLifecycleOwner(),
                textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
