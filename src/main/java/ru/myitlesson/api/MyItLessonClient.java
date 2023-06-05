package ru.myitlesson.api;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.myitlesson.api.request.CourseRequest;
import ru.myitlesson.api.request.UserRequest;
import ru.myitlesson.api.service.AuthService;
import ru.myitlesson.api.service.CourseService;
import ru.myitlesson.api.service.UserService;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MyItLessonClient {

    public static final String API_URL = "http://localhost:8000/api/";

    private final Retrofit retrofit;

    protected ApiError error;

    private final String token;

    public MyItLessonClient(String username, String password) throws IOException {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        AuthService authService = retrofit.create(AuthService.class);
        Response<JsonObject> response = authService.authorize(username, password).execute();

        error = Utils.parseErrorIfExists(this, response);

        if(error != null || response.body() == null) {
            token = "";
            return;
        }

        token = response.body().get("token").getAsString();
    }

    public UserRequest user() {
        return new UserRequest(this, retrofit.create(UserService.class));
    }

    public CourseRequest course() {
        return new CourseRequest(this, retrofit.create(CourseService.class));
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

    public <T> Converter<ResponseBody, T> getResponseBodyConverter(Type type) {
        return retrofit.responseBodyConverter(type, new Annotation[0]);
    }
}
