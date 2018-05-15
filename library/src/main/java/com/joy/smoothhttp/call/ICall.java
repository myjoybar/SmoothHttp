package com.joy.smoothhttp.call;

import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public interface ICall<T> {
	void cancel();

	void submit(Callback<T> callback);

	interface Factory {
		ICall newCall(Request request);
	}
}
