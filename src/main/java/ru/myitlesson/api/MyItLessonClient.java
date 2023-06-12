package ru.myitlesson.api;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.myitlesson.api.request.CourseRequest;
import ru.myitlesson.api.request.LessonRequest;
import ru.myitlesson.api.request.ModuleRequest;
import ru.myitlesson.api.request.UserRequest;
import ru.myitlesson.api.service.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MyItLessonClient {

    public static final String API_URL = "https://myitlesson.ru/api/";

    private final Retrofit retrofit;

    protected ApiError error;

    private final String token;

    private final long userId;

    public MyItLessonClient(String username, String password) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Response<JsonObject> response = authService.authorize(username, password).execute();

        error = Utils.parseErrorIfExists(this, response);

        if(error != null || response.body() == null) {
            token = null;
            userId = -1;
            return;
        }

        token = response.body().get("token").getAsString();
        userId = response.body().get("id").getAsLong();
    }

    public UserRequest user() {
        return new UserRequest(this, retrofit.create(UserService.class));
    }

    public CourseRequest course() {
        return new CourseRequest(this, retrofit.create(CourseService.class));
    }

    public ModuleRequest module() {
        return new ModuleRequest(this, retrofit.create(ModuleService.class));
    }

    public LessonRequest lesson() {
        return new LessonRequest(this, retrofit.create(LessonService.class));
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public String getBasic() {
        return "Basic " + token;
    }

    public long getUserId() {
        return userId;
    }

    public <T> Converter<ResponseBody, T> getResponseBodyConverter(Type type) {
        return retrofit.responseBodyConverter(type, new Annotation[0]);
    }
}
