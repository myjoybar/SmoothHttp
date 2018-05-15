package com.joy.smoothhttp.http;

import com.joy.smoothhttp.http.data.BaseResult;

/**
 * Created by joybar on 2018/5/11.
 */

public interface IHttpExecutor<TResult> {
	BaseResult<TResult> execute();

	BaseResult<TResult> execute(IRequestCallback requestCallback);
}
