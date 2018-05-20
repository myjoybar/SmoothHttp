package com.joy.smoothhttp.call;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public class RealCall<T> implements ICall {
	private SmoothHttpClient smoothHttpClient;
	private Request request;
	private Converter converter;
	private AsyncCall asyncCall;

	public RealCall(SmoothHttpClient smoothHttpClient, Request request, Converter<T> converter) {
		this.smoothHttpClient = smoothHttpClient;
		this.request = request;
		this.converter = converter;
	}

	@Override
	public void cancel() {
		if (null != asyncCall) {
			asyncCall.cancel();
		}
	}

	@Override
	public void submit(Callback callback) {
		asyncCall = new AsyncCall(smoothHttpClient,this, request, converter, callback);
		asyncCall.execute();
	}



}
