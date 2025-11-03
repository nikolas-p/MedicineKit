package com.example.medkit.ui.medications.intakes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medkit.R;

public class EditIntakesLogFragment extends Fragment {

    private EditIntakesLogViewModel mViewModel;

    public static EditIntakesLogFragment newInstance() {
        return new EditIntakesLogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_intakes_log, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditIntakesLogViewModel.class);
        // TODO: Use the ViewModel
    }

}