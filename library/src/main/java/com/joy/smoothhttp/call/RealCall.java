package com.joy.smoothhttp.call;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public class RealCall<T> implements ICall {
	SmoothHttpClient smoothHttpClient;
	Request request;
	Converter converter;

	public RealCall(SmoothHttpClient smoothHttpClient, Request request,Converter<T> converter) {
		this.smoothHttpClient = smoothHttpClient;
		this.request = request;
		this.converter = converter;
	}

	@Override
	public void cancel() {

	}

	@Override
	public void submit(Callback callback) {
		AsyncCall asyncCall = new AsyncCall(this,request,converter,callback);
		asyncCall.execute();
	}






}
