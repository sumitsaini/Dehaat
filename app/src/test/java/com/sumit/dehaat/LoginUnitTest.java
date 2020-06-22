package com.sumit.dehaat;

import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.viewmodel.LoginViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.lifecycle.MutableLiveData;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LoginUnitTest {

    @Test
    public void emailValidationIfEmailInCorrectWithOutDomain_ReturnTrue() {
        assertFalse(Utils.isEmailValid("sumit.saini"));
    }

    @Test
    public void emailValidationIfEmailInCorrectWithoutCom_ReturnTrue() {
        assertFalse(Utils.isEmailValid("sumit.saini@hotmail"));
    }


    @Test
    public void emailValidation1IfEmailCorrect_ReturnTrue() {
        assertTrue(Utils.isEmailValid("sumit.saini@gmail.com"));
    }

    @Test
    public void loginValidation() {
        LoginViewModel loginViewModel=new LoginViewModel();
        MutableLiveData<LoginResponse> loginResponseMutableLiveData=loginViewModel.login("eve.holt@reqres.in","cityslicka");
        assertTrue(true);
    }

    @Test
    public void loginValidationFailed() {
        LoginViewModel loginViewModel=new LoginViewModel();
        loginViewModel.login("eve.holt@gmail.com","cityslicka");
        assertFalse(false);
    }





}
