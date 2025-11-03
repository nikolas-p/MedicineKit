package com.example.medkit.ui.medications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MedicationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is medications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}