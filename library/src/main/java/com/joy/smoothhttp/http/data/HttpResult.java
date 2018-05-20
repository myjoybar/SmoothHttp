package com.joy.smoothhttp.http.data;

/**
 * Created by joybar on 15/05/2018.
 */

public class HttpResult {
	private Throwable throwable;
	private byte[] bytes;
	private int contentLength;
	private String responseStr;

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

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}
}
