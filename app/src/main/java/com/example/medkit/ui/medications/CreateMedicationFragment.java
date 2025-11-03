package com.example.medkit.ui.medications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.medkit.R;
import com.example.medkit.databinding.FragmentCreateMedicationBinding;

public class CreateMedicationFragment extends Fragment {

    private FragmentCreateMedicationBinding binding;
    private CreateMedicationViewModel viewModel;
    private Button btnEditCard, btnEditMedia;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateMedicationBinding.inflate(inflater, container, false);

        btnEditCard = binding.editcard;
        btnEditMedia = binding.editmedia;

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CreateMedicationViewModel.class);


        setupToolbar();
        setupCreateButton();
        setupEditCardButton();
        setupEditMediaButton();
    }

    private void setupEditMediaButton() {

        btnEditMedia.setOnClickListener(v->
                {
                    Navigation.findNavController(v).navigate(R.id.action_create_medication_to_medicine_edit_media);
                });
       }

    private void setupEditCardButton() {
        btnEditCard.setOnClickListener(v -> {
            // Выполняем навигацию при нажатии
            Navigation.findNavController(v).navigate(R.id.action_create_medication_to_medicine_edit_card);
        });
    }


    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });

        binding.btnOptions.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_to_quick_medication);
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