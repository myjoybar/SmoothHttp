package com.joy.smoothhttp.http.data;

import java.io.InputStream;

/**
 * Created by joybar on 15/05/2018.
 */

public class HttpResult {
	InputStream inputStream;
	Throwable throwable;

	public HttpResult() {
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
