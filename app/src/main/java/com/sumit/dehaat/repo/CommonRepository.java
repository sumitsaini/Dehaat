package com.sumit.dehaat.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.sumit.dehaat.Utils;
import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.repo.services.CommonApi;
import com.sumit.dehaat.repo.services.RetrofitService;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class help to interact with service calls and return data back
 */
public class CommonRepository {


    private CommonApi commonApi;
    private final String TAG = CommonRepository.class.getSimpleName();

    public CommonRepository() {
        commonApi = RetrofitService.getInstance().create(CommonApi.class);
    }

    /**
     * This method help login with users
     *
     * @param email    Input email
     * @param password input password
     * @return response data
     */
    public MutableLiveData<LoginResponse> login(String email, String password) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);

        final MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<>();

        commonApi.login(jsonObject).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.raw().code() == 200) {
                    LoginResponse loginResponse = response.body();
                    loginResponse.setHttpCode(Utils.HTTP_SUCCESS);
                    mutableLiveData.postValue(response.body());
                } else {
                    LoginResponse loginResponse = new LoginResponse();
                    loginResponse.setHttpCode(Utils.HTTP_FAILURE);
                    mutableLiveData.postValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setHttpCode(Utils.HTTP_FAILURE);
                mutableLiveData.postValue(loginResponse);
            }
        });

        return mutableLiveData;
    }


}
