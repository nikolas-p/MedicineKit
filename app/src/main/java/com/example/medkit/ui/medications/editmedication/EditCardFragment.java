package com.example.medkit.ui.medications.editmedication;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentEditCardBinding;
import com.example.medkit.ui.medications.CreateMedicationViewModel;

public class EditCardFragment extends Fragment {

    private EditCardViewModel viewModel;

    private FragmentEditCardBinding binding;
    private ImageButton btnSaveChanges;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentEditCardBinding.inflate(inflater, container, false);

        btnSaveChanges = binding.btnSaveChanges;

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(EditCardViewModel.class);


        setupToolbar();
        //setupCreateButton();

    }

    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}