package com.joy.smoothhttp.call;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public class RealCall<T> implements ICall {
	SmoothHttpClient smoothHttpClient;
	Request request;

	public RealCall(SmoothHttpClient smoothHttpClient, Request request) {
		this.smoothHttpClient = smoothHttpClient;
		this.request = request;
	}

	@Override
	public void cancel() {

	}

	@Override
	public void submit(Callback callback) {
		AsyncCall asyncCall = new AsyncCall(this,request,callback);
		asyncCall.execute();
	}


}
