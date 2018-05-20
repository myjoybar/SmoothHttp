package com.joy.smoothhttp.response;

import com.joy.smoothhttp.request.url.HttpUrl;
import com.joy.smoothhttp.response.body.ResponseBody;

/**
 * Created by joybar on 2018/5/16.
 */

public class Response implements Cloneable {
    private HttpUrl httpUrl;
    private ResponseBody responseBody;
    private Throwable throwable;
    private long responseTime;

    public ResponseBody getResponseBody() {
        return responseBody;
    }


    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
