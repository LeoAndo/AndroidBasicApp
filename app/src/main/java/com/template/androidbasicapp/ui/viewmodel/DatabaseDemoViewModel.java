package com.template.androidbasicapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DatabaseDemoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }

    public DatabaseDemoViewModel() {
        mText = new MutableLiveData<>();
        mText.postValue("This is gallery fragment");
    }
}