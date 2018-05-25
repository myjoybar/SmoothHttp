package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.response.Response;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/16.
 */

public class ResponseConverter<T> implements Converter<T> {


	@Override
	public T convert(Response response) throws IOException {
		return (T) response;

	}

}
