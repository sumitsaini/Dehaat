package com.sumit.dehaat.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.repo.CommonRepository;

public class LoginViewModel extends AndroidViewModel {

    private CommonRepository commonRepository;

    public LoginViewModel(Application application) {
        super(application);

        commonRepository = new CommonRepository(application.getCacheDir());
    }


    public MutableLiveData<LoginResponse> login(String email, String password) {
        return commonRepository.login(email, password);
    }


}
