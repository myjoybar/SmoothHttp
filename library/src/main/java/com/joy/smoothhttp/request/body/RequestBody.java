package com.joy.smoothhttp.request.body;

import com.joy.smoothhttp.utils.CheckUtils;
import com.joy.smoothhttp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 2018/5/14.
 */

public class RequestBody {
	private final List<String> encodedNames;
	private final List<String> encodedValues;


	RequestBody(Builder builder) {
		this.encodedNames = Utils.immutableList(builder.names);
		this.encodedValues = Utils.immutableList(builder.values);
	}

	public List<String> getEncodedNames() {
		return encodedNames;
	}

	public List<String> getEncodedValues() {
		return encodedValues;
	}

	public static final class Builder {
		private final List<String> names = new ArrayList<>();
		private final List<String> values = new ArrayList<>();

		public Builder add(String name, String value) {
			CheckUtils.checkNotNull(name);
			CheckUtils.checkNotNull(value);
			names.add(name);
			values.add(value);
			return this;
		}

		public Builder addEncoded(String name, String value) {
			CheckUtils.checkNotNull(name);
			CheckUtils.checkNotNull(value);
			names.add(name);
			values.add(value);
			return this;
		}

		public RequestBody build() {
			return new RequestBody(this);
		}
	}
}
