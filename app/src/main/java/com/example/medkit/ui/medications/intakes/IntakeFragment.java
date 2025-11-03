package com.example.medkit.ui.medications.intakes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentIntakeBinding;
import com.example.medkit.databinding.FragmentMedicationBinding;

public class IntakeFragment extends Fragment {

    private IntakeViewModel viewModel;
    private FragmentIntakeBinding binding;

    public static IntakeFragment newInstance() {
        return new IntakeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIntakeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(IntakeViewModel.class);
        setupToolbar();
    }

    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
    }

}