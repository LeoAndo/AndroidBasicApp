package com.template.androidbasicapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreferencesDemoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PreferencesDemoViewModel() {
        mText = new MutableLiveData<>();
        mText.postValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}