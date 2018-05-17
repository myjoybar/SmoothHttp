package com.joy.smoothhttp.call;

import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public interface ICall<T> {
	void cancel();

	void submit(Callback<T> callback);

	interface Factory {
		ICall newCall(Request request,Converter converter);
//		ICall newStringCall(Request request);
//		ICall newBitmapCall(Request request);
//		ICall newJsonCall(Request request);
//		Iall newModleCall(Request request);
	}
}
