package com.joy.smoothhttp.http;

import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.request.Request;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/11.
 */

public abstract class AbstractHttpExecutor implements IHttpExecutor {
    @Override
    public HttpResult execute() {
        return null;
    }

    @Override
    public HttpResult execute(IProgress progress) throws IOException {
        return null;
    }

    protected Request request;

    public Request getRequest() {
        return request;
    }

    public AbstractHttpExecutor(Request request) {
        this.request = request;
    }


}
