package com.example.medkit.ui.medications;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentCreateMedicationBinding;
import com.example.medkit.databinding.FragmentQuickMedicationsBinding;

public class QuickMedicationsFragment extends Fragment {

    private QuickMedicationsViewModel viewModel;
    private FragmentQuickMedicationsBinding binding;

    public static QuickMedicationsFragment newInstance() {
        return new QuickMedicationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuickMedicationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(QuickMedicationsViewModel.class);
        setupToolbar(); // Исправлено название метода
    }

    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.btnSearch.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "бизнес логика поиска", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}