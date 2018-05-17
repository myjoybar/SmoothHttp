package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.utils.GsonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by joybar on 2018/5/16.
 */


public class GsonListConverter<T> implements Converter<List<T>> {
	private static String TAG = "GsonListConverter";
	Class<T> type;
	public GsonListConverter(Class<T> type) {
		this.type = type;
	}
	@Override
	public List<T> convert(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[2048];
		int readBytes = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			stringBuilder.append(new String(buffer, 0, readBytes));
		}
		String jsonStr = stringBuilder.toString();
		return GsonUtil.parseJsonArrayStrToList(jsonStr, type);
	}

}
