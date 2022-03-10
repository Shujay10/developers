package com.example.developers.ui.aboutus;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutUsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    TextView link;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is about us fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}