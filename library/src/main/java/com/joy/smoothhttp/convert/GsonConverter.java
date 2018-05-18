package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.utils.GsonUtil;

import java.io.IOException;

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
	public T convert(byte[] bytes) throws IOException {
		String jsonStr = new String(bytes, "UTF-8");
		return GsonUtil.parseJsonStrToBean(jsonStr, type);


	}
}
