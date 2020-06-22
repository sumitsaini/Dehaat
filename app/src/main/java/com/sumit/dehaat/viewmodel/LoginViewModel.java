package com.sumit.dehaat.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.repo.CommonRepository;

public class LoginViewModel extends ViewModel {

    private CommonRepository commonRepository;

    public LoginViewModel() {
        commonRepository = new CommonRepository();
    }


    public MutableLiveData<LoginResponse> login(String email, String password) {
        return commonRepository.login(email, password);
    }


}
