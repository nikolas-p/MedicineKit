package com.example.medkit.ui.intakes;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.medkit.R;
import com.example.medkit.databinding.FragmentNotificationsBinding;
import com.example.medkit.ui.medications.MedicationsViewModel;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private NotificationsViewModel viewModel;
    private boolean isOptionsMenuShowing = false;
    private boolean isCalendarShowing = true; // По умолчанию показываем календарь

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        setupOptionsMenu();
        setupCalendarSwitcher();
        setupNavMedCourses();
        setupNavCreateIntake();
        setupNavCreateIntakeBtn();
        setupToolbar();
    }

    private void setupNavCreateIntakeBtn() {
        binding.btnNavCreateIntake.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.create_intake);
        });
    }

    private void setupNavCreateIntake() {
        binding.navCreateIntake.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.create_intake);
        });

    }

    private void setupToolbar() {
        // Кнопка добавления (плюсик)
        binding.btnAdd.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.edit_med_course);
        });
    }
    private void setupNavMedCourses() {

        binding.optionActualMedCourse.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.med_courses);
        });
    }

    private void setupOptionsMenu() {
        binding.btnOptions.setOnClickListener(v -> {
            if (isOptionsMenuShowing) {
                hideOptionsMenu();
            } else {
                showOptionsMenu();
            }
        });

        binding.optionsMenu.setOnClickListener(v -> {
            v.setOnTouchListener((v1, event) -> {
                v1.performClick();
                return true;
            });
        });
    }

    private void setupCalendarSwitcher() {
        // Обработчик для переключения на обычный календарь
        binding.optionCalendar.setOnClickListener(v -> {
            if (!isCalendarShowing) {
                showCalendar();
                hideOptionsMenu();
            }
        });

        // Обработчик для переключения на недельный календарь
        binding.optionWeeklyCalendar.setOnClickListener(v -> {
            if (isCalendarShowing) {
                showWeeklyCalendar();
                hideOptionsMenu();
            }
        });

        // Изначально показываем календарь
        showCalendar();
    }

    private void showCalendar() {
        isCalendarShowing = true;

        // Показываем календарь, скрываем недельный календарь
        binding.calendar.setVisibility(View.VISIBLE);
        binding.weeklyCalendar.setVisibility(View.GONE);

        // Обновляем стили кнопок (опционально)
        updateOptionStyles();
    }

    private void showWeeklyCalendar() {
        isCalendarShowing = false;

        // Показываем недельный календарь, скрываем обычный календарь
        binding.weeklyCalendar.setVisibility(View.VISIBLE);
        binding.calendar.setVisibility(View.GONE);

        // Обновляем стили кнопок (опционально)
        updateOptionStyles();
    }

    private void updateOptionStyles() {
        // Меняем стили текстовых view для визуального выделения активной опции
        if (isCalendarShowing) {
            binding.optionCalendar.setTextColor(getResources().getColor(R.color.purple_500, null));
            binding.optionWeeklyCalendar.setTextColor(getResources().getColor(R.color.gray_light, null));
        } else {
            // Подсвечиваем option_weekly_calendar, убираем подсветку с option_calendar

            binding.optionWeeklyCalendar.setTextColor(getResources().getColor(R.color.purple_500, null));
            binding.optionCalendar.setTextColor(getResources().getColor(R.color.gray_light, null));
        }
    }

    private void showOptionsMenu() {
        if (isOptionsMenuShowing) return;
        isOptionsMenuShowing = true;
        binding.optionsMenu.setVisibility(View.VISIBLE);
        binding.optionsMenu.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.scale_up));

        binding.getRoot().setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (isOptionsMenuShowing) {
                    Rect outRect = new Rect();
                    binding.optionsMenu.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        hideOptionsMenu();
                    }
                }
            }
            return false;
        });
    }

    private void hideOptionsMenu() {
        if (!isOptionsMenuShowing) return;
        isOptionsMenuShowing = false;
        binding.optionsMenu.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.scale_down));
        binding.optionsMenu.setVisibility(View.GONE);

        binding.getRoot().setOnTouchListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}