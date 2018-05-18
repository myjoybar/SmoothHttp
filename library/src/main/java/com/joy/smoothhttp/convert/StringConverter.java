package com.joy.smoothhttp.convert;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/16.
 */

public class StringConverter<T> implements Converter<T> {


	@Override
	public T convert(byte[] bytes) throws IOException {
		return (T) new String(bytes, "UTF-8");

	}

}
