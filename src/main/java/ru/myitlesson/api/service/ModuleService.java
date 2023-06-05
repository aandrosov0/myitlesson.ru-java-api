package ru.myitlesson.api.service;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;
import ru.myitlesson.api.entity.ModuleEntity;

public interface ModuleService {

    @GET("module/{id}")
    Call<ModuleEntity> get(@Header("Authorization") String token, @Path("id") int id);

    @FormUrlEncoded
    @POST("module")
    Call<JsonObject> add(@Header("Authorization") String token, @Field("title") String title, @Field("content") String content, @Field("courseId") int courseId);

    @DELETE("module/{id}")
    Call<JsonObject> delete(@Header("Authorization") String token, @Path("id") int id);
}
