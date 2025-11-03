package com.example.medkit.ui.intakes;

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
import com.example.medkit.databinding.FragmentCreateIntakeBinding;
import com.example.medkit.databinding.FragmentIntakeLogBinding;

public class CreateIntakeFragment extends Fragment {

    private CreateIntakeViewModel viewModel;
    private FragmentCreateIntakeBinding binding;

    public static CreateIntakeFragment newInstance() {
        return new CreateIntakeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCreateIntakeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CreateIntakeViewModel.class);

        setupToolbar();
    }

    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
    }

}