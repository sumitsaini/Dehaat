package com.sumit.dehaat.repo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.sumit.dehaat.model.Author;
import com.sumit.dehaat.model.Book;
import com.sumit.dehaat.model.LoginResponse;
import com.sumit.dehaat.repo.database.AppDatabase;
import com.sumit.dehaat.repo.services.CommonApi;
import com.sumit.dehaat.repo.services.RetrofitService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class help to interact with service calls and return data back
 */
public class CommonRepository {

    private CommonApi commonApi;
    private final String TAG = CommonRepository.class.getSimpleName();

    public CommonRepository(File fileCache) {
        commonApi = RetrofitService.getInstance(fileCache).create(CommonApi.class);
    }

    /**
     * This method help login with users
     * @param email Input email
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
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;
    }


}
