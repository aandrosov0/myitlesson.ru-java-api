package ru.myitlesson.api.service;

import com.google.gson.JsonObject;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.myitlesson.api.entity.CourseEntity;

import java.util.List;

public interface CourseService {

    @GET("course/{id}")
    Call<CourseEntity> get(@Header("Authorization") String token, @Path("id") int id);

    @GET("courses/{limit}/offset/{offset}")
    Call<List<CourseEntity>> getAmount(@Header("Authorization") String token, @Path("limit") int limit, @Path("offset") int offset);

    @FormUrlEncoded
    @POST("course")
    Call<JsonObject> add(@Header("Authorization") String token, @Field("title") String title, @Field("content") String content, @Field("authorId") int authorId);

    @DELETE("course/{id}")
    Call<JsonObject> delete(@Header("Authorization") String token, @Path("id") int id);

    @GET("course/{id}/image")
    Call<ResponseBody> getImage(@Header("Authorization") String token, @Path("id") int id);

    @PUT("course/{id}/image")
    Call<JsonObject> setImage(@Header("Authorization") String token, @Path("id") int id, @Body RequestBody image);
}
