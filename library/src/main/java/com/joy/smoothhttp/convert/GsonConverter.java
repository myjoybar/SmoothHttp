package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.utils.GsonUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public class GsonConverter<T> implements Converter<T> {
	private static String TAG = "GsonConverter";
	Class<T> type;

	public GsonConverter(Class<T> type) {
		this.type = type;
	}

	@Override
	public T convert(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[2048];
		int readBytes = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			stringBuilder.append(new String(buffer, 0, readBytes));
		}
		String jsonStr = stringBuilder.toString();
		return GsonUtil.parseJsonStrToBean(jsonStr, type);


	}
}
