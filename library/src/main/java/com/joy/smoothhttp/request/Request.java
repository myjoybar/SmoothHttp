package com.joy.smoothhttp.request;

import com.joy.smoothhttp.request.body.RequestBody;
import com.joy.smoothhttp.request.header.RequestHeader;
import com.joy.smoothhttp.request.url.HttpUrl;

/**
 * Created by joybar on 2018/5/14.
 */

public final class Request {

    private HttpUrl httpUrl;
    private final RequestHeader header;
    private final RequestBody requestBody;
    private final String method;
    private final int timeOut;
    private final int redirects;
    private final int maxRunningTaskNumber;


    public Request(Builder builder) {
        httpUrl = builder.httpUrl;
        header = builder.header;
        requestBody = builder.requestBody;
        method = builder.method;
        timeOut = builder.timeOut;
        redirects = builder.redirects;
        maxRunningTaskNumber = builder.maxRunningTaskNumber;
    }


    public HttpUrl getHttpUrl() {
        return httpUrl;
    }

    public void SetHttpUrl(HttpUrl httpUrl) {
        this.httpUrl = httpUrl;
    }


    public RequestHeader getHeader() {
        return header;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public String getMethod() {
        return method;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public int getRedirects() {
        return redirects;
    }

    public int getMaxRunningTaskNumber() {
        return maxRunningTaskNumber;
    }

    public static class Builder {
        HttpUrl httpUrl;
        RequestHeader header;
        RequestBody requestBody;
        String method;
        int timeOut = 5 * 1000;
        int redirects = 5;
        int maxRunningTaskNumber = 64;

        public Builder setHttpUrl(String url) {
            this.httpUrl = new HttpUrl.Builder().setUrl(url).build();
            return this;
        }

        public Builder setHeader(RequestHeader header) {
            this.header = header;
            return this;
        }

        public Builder setRequestBody(RequestBody requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public Builder setTimeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public Builder setRedirects(int redirects) {
            this.redirects = redirects;
            return this;
        }

        public Builder setMaxRunningTaskNumber(int maxRunningTaskNumber) {
            this.maxRunningTaskNumber = maxRunningTaskNumber;
            return this;
        }

        public Request build() {
            return new Request(this);
        }


    }
}
