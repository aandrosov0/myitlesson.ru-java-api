package ru.myitlesson.api.service;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {

    @FormUrlEncoded
    @POST("auth")
    Call<JsonObject> authorize(@Field("username") String username, @Field("password") String password);
}
