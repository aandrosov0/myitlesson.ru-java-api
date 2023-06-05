package ru.myitlesson.api;

import com.google.gson.annotations.SerializedName;

public class ApiError {

    public enum Code {

        @SerializedName("0")
        NUMBER_FORMAT,

        @SerializedName("1")
        FILE_FORMAT,

        REQUEST_SYNTAX,

        AUTHORIZATION,

        NOT_FOUND,

        SERVER
    }

    private String message;

    private Code code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
