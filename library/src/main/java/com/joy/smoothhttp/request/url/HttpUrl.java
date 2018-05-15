package com.joy.smoothhttp.request.url;

/**
 * Created by joybar on 2018/5/14.
 */

public class HttpUrl {
	private final String url;

	public HttpUrl(Builder builder) {
		this.url = builder.url;
	}

	public String getUrl() {
		return url;
	}

	public static final class Builder {
		String url;

		public Builder setUrl(String url) {
			this.url = url;
			return this;
		}

		public HttpUrl build() {
			return new HttpUrl(this);
		}

	}
}
