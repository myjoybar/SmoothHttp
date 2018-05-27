package com.joy.smoothhttp.http;

import com.joy.smoothhttp.http.data.HttpResult;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/11.
 */

public interface IHttpExecutor {
	HttpResult execute();
	HttpResult execute(IProgress progress) throws IOException;
	void cancel();
}
