package com.joy.smoothhttp.convert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public interface Converter<T> {
	T convert(InputStream inputStream) throws IOException;
}