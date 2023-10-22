package com.template.androidbasicapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NetworkDemoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NetworkDemoViewModel() {
        mText = new MutableLiveData<>();
        mText.postValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}