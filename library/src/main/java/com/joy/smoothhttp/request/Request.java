package com.joy.smoothhttp.request;

import com.joy.smoothhttp.request.body.RequestBody;
import com.joy.smoothhttp.request.header.RequestHeader;
import com.joy.smoothhttp.request.url.HttpUrl;

/**
 * Created by joybar on 2018/5/14.
 */

public final class Request {

	private final HttpUrl httpUrl;
	private final RequestHeader header;
	private final RequestBody requestBody;
	private final String method;

	public Request(Builder builder) {
		httpUrl = builder.httpUrl;
		header = builder.header;
		requestBody = builder.requestBody;
		method = builder.method;
	}


	public HttpUrl getHttpUrl() {
		return httpUrl;
	}

	public RequestHeader getHeader() {
		return header;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public String getMethod() {
		return method;
	}

	public static class Builder {
		HttpUrl httpUrl;
		RequestHeader header;
		RequestBody requestBody;
		String method;

		public Builder setHttpUrl(String url) {
			this.httpUrl = new HttpUrl.Builder().setUrl(url).build();
			;
			return this;
		}

		public Builder setHeader(RequestHeader header) {
			this.header = header;
			return this;
		}

		public Builder setRequestBody(RequestBody requestBody) {
			this.requestBody = requestBody;
			return this;
		}

		public Builder setMethod(String method) {
			this.method = method;
			return this;
		}

		public Request build() {
			return new Request(this);
		}


	}
}
