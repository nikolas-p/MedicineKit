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

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentEditCardBinding;
import com.example.medkit.databinding.FragmentEditMediaBinding;

public class EditMediaFragment extends Fragment {

    private EditMediaViewModel viewModel;

    private FragmentEditMediaBinding binding;

    public static EditMediaFragment newInstance() {
        return new EditMediaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditMediaBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EditMediaViewModel.class);

        setupToolbar();
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