package com.example.healthbuddy.patient.dashboard.ui.schedule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScheduleGalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ScheduleGalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}