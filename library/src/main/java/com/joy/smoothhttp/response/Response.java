package com.joy.smoothhttp.response;

import com.joy.smoothhttp.response.body.ResponseBody;

/**
 * Created by joybar on 2018/5/16.
 */

public class Response {
	private ResponseBody responseBody;
	private Throwable throwable;
	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
}
