package com.joy.smoothhttp.http;

/**
 * Created by joybar on 2018/5/7.
 */

public interface IRequestCallback<TResult> {

	void onSuccess(TResult response);

	void onFailure(Throwable throwable);
}
