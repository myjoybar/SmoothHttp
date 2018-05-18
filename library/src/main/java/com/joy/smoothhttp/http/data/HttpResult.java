package com.joy.smoothhttp.http.data;

/**
 * Created by joybar on 15/05/2018.
 */

public class HttpResult {
	private Throwable throwable;
	private byte[] bytes;

	public HttpResult() {
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
