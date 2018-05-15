package com.joy.smoothhttp.http;

import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/11.
 */

public abstract class AbstractHttpExecutor implements IHttpExecutor {
    protected Request request;

    public Request getRequest() {
        return request;
    }

    public AbstractHttpExecutor(Request request) {
        this.request = request;
    }
}
