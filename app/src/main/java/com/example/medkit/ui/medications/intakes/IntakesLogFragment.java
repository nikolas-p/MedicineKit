package com.example.medkit.ui.medications.intakes;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentCreateMedicationBinding;
import com.example.medkit.databinding.FragmentIntakeLogBinding;
import com.example.medkit.ui.medications.CreateMedicationViewModel;

public class IntakesLogFragment extends Fragment {

    private FragmentIntakeLogBinding binding;
    private IntakesLogViewModel viewModel;
    private boolean isOptionsMenuShowing = false;

    public static IntakesLogFragment newInstance() {
        return new IntakesLogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentIntakeLogBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(IntakesLogViewModel.class);

        setupToolbar();
        setupOptionsMenu();
        setupNavIntake();
        setupNavCreateIntake();
    }

    private void setupNavCreateIntake() {
        binding.navCreateIntake.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.create_intake);
        });
    }
    private void setupNavIntake() {
        binding.intakeItem.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.navigation_intake);
        });
    }


    private void setupToolbar() {
        // Кнопка назад
        binding.btnBack.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack();
        });
    }

    private void setupOptionsMenu() {
        // Предположим, что у нас есть binding.optionsMenu (LinearLayout) и binding.btnOptions (кнопка)
        binding.btnOptions.setOnClickListener(v -> {
            if (isOptionsMenuShowing) {
                hideOptionsMenu();
            } else {
                showOptionsMenu();
            }
        });

        // При нажатии на само меню мы не хотим, чтобы оно закрывалось, поэтому остановим распространение события
        binding.optionsMenu.setOnClickListener(v -> {
            // Ничего не делаем, просто останавливаем всплытие события
            v.setOnTouchListener((v1, event) -> {
                v1.performClick();
                return true; // consume the event
            });
        });
    }

    private void showOptionsMenu() {
        if (isOptionsMenuShowing) return;
        isOptionsMenuShowing = true;
        binding.optionsMenu.setVisibility(View.VISIBLE);
        binding.optionsMenu.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up));

        // Устанавливаем слушатель на корневой view для закрытия меню при нажатии вне его
        binding.getRoot().setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (isOptionsMenuShowing) {
                    // Проверяем, не находится ли точка нажатия внутри optionsMenu
                    Rect outRect = new Rect();
                    binding.optionsMenu.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        hideOptionsMenu();
                    }
                }
            }
            return false; // не потребляем событие, чтобы другие view тоже могли его обработать
        });
    }

    private void hideOptionsMenu() {
        if (!isOptionsMenuShowing) return;
        isOptionsMenuShowing = false;
        binding.optionsMenu.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.scale_down));
        binding.optionsMenu.setVisibility(View.GONE);

        // Убираем слушатель, чтобы не обрабатывать лишние события
        binding.getRoot().setOnTouchListener(null);
    }

}