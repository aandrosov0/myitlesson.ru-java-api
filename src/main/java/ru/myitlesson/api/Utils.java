package ru.myitlesson.api;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import java.io.IOException;

public final class Utils {

    public static ApiError parseErrorIfExists(MyItLessonClient client, Response<?> response) {
        ResponseBody responseBody = response.errorBody();

        if(response.isSuccessful() || responseBody == null) {
            return null;
        }

        Converter<ResponseBody, ApiError> converter = client.getResponseBodyConverter(ApiError.class);
        ApiError error;

        try {
            error = converter.convert(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        responseBody.close();
        if(error == null) {
            return null;
        }

        ApiError.Code code = error.getCode();

        if(code == null) {
            switch(response.code()) {
                case 400:
                    error.setCode(ApiError.Code.REQUEST_SYNTAX);
                    break;
                case 401:
                    error.setCode(ApiError.Code.AUTHORIZATION);
                    break;
                case 404:
                    error.setCode(ApiError.Code.NOT_FOUND);
                    break;
                case 500:
                    error.setCode(ApiError.Code.SERVER);
                    break;
            }
        }

        return  error;
    }
}
