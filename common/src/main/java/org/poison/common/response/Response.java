package org.poison.common.response;

import lombok.Data;

@Data
public class Response<T> {
    protected ResponseCode code;

    protected String msg;

    protected T content;

    public Response(ResponseCode code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public static <T> Response<T> success(String msg) {
        return new Response<>(ResponseCode.SUCCESS, msg, null);
    }

    public static <T> Response<T> success(T content) {
        return new Response<>(ResponseCode.SUCCESS, null, content);
    }

    public static <T> Response<T> success(String msg, T content) {
        return new Response<>(ResponseCode.SUCCESS, msg, content);
    }

    public static <T> Response<T> fail(String msg) {
        return new Response<>(ResponseCode.FAIL, msg, null);
    }

    public static <T> Response<T> fail(String msg, T content) {
        return new Response<>(ResponseCode.FAIL, msg, content);
    }
}
