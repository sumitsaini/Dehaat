package com.sumit.dehaat.repo.services;

import com.google.gson.JsonObject;
import com.sumit.dehaat.model.LoginResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sumitsaini on 17/06/2019 AD.
 */

public interface CommonApi {

    @POST("/api/login")
    Call<LoginResponse> login(@Body JsonObject jsonObject);


}
