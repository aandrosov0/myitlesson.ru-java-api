package ru.myitlesson.api.service;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;
import ru.myitlesson.api.entity.LessonEntity;

public interface LessonService {

    @GET("lesson/{id}")
    Call<LessonEntity> get(@Header("Authorization") String token, @Path("id") int id);

    @FormUrlEncoded
    @POST("lesson")
    Call<JsonObject> add(@Header("Authorization") String token, @Field("title") String title, @Field("content") String content, @Field("moduleId") int moduleId);

    @DELETE("lesson/{id}")
    Call<JsonObject> delete(@Header("Authorization") String token, @Path("id") int id);
}
