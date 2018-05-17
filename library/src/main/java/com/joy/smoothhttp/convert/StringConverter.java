package com.joy.smoothhttp.convert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public class StringConverter<T> implements Converter<T>{

	private static String TAG = "StringConverter";

	@Override
	public T convert(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[2048];
		int readBytes = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			stringBuilder.append(new String(buffer, 0, readBytes));
		}
		return (T) stringBuilder.toString();
	}

}
