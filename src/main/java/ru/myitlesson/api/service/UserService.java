package ru.myitlesson.api.service;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;
import ru.myitlesson.api.entity.UserEntity;

import java.util.List;

public interface UserService {

    @GET("user/{id}")
    Call<UserEntity> get(@Header("Authorization") String token, @Path("id") int id);

    @GET("users/{limit}/offset/{offset}")
    Call<List<UserEntity>> getAmount(@Header("Authorization") String token, @Path("limit") int limit, @Path("offset") int offset);

    @FormUrlEncoded
    @POST("user")
    Call<JsonObject> add(@Header("Authorization") String token, @Field("username") String username, @Field("password") String password, @Field("role") int role);

    @DELETE("user/{id}")
    Call<JsonObject> delete(@Header("Authorization") String token, @Path("id") int id);
}
