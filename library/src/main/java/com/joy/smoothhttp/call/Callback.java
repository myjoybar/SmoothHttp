package com.joy.smoothhttp.call;

/**
 * Created by joybar on 2018/5/14.
 */

public interface Callback<TResponse> {
    void onFailure(ICall call, Throwable throwable);

    void onResponse(ICall call, TResponse response);

    void onProgressUpdate(ICall call, int values);
}
