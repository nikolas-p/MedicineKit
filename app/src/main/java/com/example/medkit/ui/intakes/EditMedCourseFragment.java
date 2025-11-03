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
import com.example.medkit.databinding.FragmentCreateMedicationBinding;
import com.example.medkit.databinding.FragmentEditMedCourseBinding;
import com.example.medkit.ui.medications.CreateMedicationViewModel;

public class EditMedCourseFragment extends Fragment {

    private EditMedCourseViewModel viewModel;
    private FragmentEditMedCourseBinding binding;

    public static EditMedCourseFragment newInstance() {
        return new EditMedCourseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditMedCourseBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(EditMedCourseViewModel.class);


        setupToolbar();
        setupCreateButton();
    }

    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

    }

    private void setupCreateButton() {
        binding.btnCreate.setOnClickListener(v -> {
            // Логика создания медикамента
            // После успешного создания возвращаемся назад
            Navigation.findNavController(v).popBackStack();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}