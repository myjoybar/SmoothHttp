package com.joy.smoothhttp.interceptor.interfaces;

/**
 * Created by joybar on 19/05/2018.
 */

import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;

import java.io.IOException;


public interface IInterceptor {
    Response intercept(Chain chain) throws IOException;

    interface Chain {
        Request request();

        Response proceed(Request request) throws IOException;

    }
}
