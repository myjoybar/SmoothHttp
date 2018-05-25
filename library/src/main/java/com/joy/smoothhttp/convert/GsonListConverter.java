package com.joy.smoothhttp.convert;

import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.utils.GsonUtil;

import java.io.IOException;
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
	public List<T> convert(Response response) throws IOException {
		byte[] bytes = response.getResponseBody().getBytes();
		String jsonStr =  new String(bytes, "UTF-8");
		return GsonUtil.parseJsonArrayStrToList(jsonStr, type);
	}

}
