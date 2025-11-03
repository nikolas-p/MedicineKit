package com.example.medkit;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.medkit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private ActivityMainBinding binding;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация базы данных ДО навигации
        initializeDatabase();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_settings, R.id.navigation_medications,
                R.id.navigation_notifications, R.id.navigation_search)
                .build();
      NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       NavigationUI.setupWithNavController(binding.navView, navController);


        // Слушатель изменений текущего фрагмента
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                // Список идентификаторов фрагментов, на которых нужно скрыть нижнюю навигацию
                int[] hideBottomNavIds = {
                        R.id.navigation_quick_medications,
                        R.id.create_medication,
                        R.id.medicine_edit_card,
                        R.id.medicine_edit_media,
                        R.id.navigation_medication,
                        R.id.edit_med_course,
                        R.id.med_course,
                        R.id.med_courses,
                        R.id.navigation_intakes,
                        R.id.navigation_intake,
                        R.id.create_intake
                };

                boolean showBottomNav = true;
                for (int id : hideBottomNavIds) {
                    if (destination.getId() == id) {
                        showBottomNav = false;
                        break;
                    }
                }

                if (showBottomNav) {
                    binding.navView.setVisibility(View.VISIBLE);
                } else {
                    binding.navView.setVisibility(View.GONE);
                }
            }
        });

    }


    private void initializeDatabase() {
        try {
            // Создание экземпляра DatabaseHelper
            dbHelper = new DatabaseHelper(this);

            // Получаем базу данных для записи (это вызовет onCreate если БД не существует)
            database = dbHelper.getWritableDatabase();

            Log.d(TAG, "База данных успешно создана/открыта");

        } catch (Exception e) {
            Log.e(TAG, "Ошибка при создании базы данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
        if (dbHelper != null) {
            dbHelper.close();
        }
        Log.d(TAG, "База данных закрыта");
    }
}