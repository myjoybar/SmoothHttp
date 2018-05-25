package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.response.Response;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/16.
 */

public interface Converter<T> {
	T convert(Response response) throws IOException;
}