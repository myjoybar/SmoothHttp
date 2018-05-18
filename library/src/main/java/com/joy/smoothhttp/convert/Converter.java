package com.joy.smoothhttp.convert;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/16.
 */

public interface Converter<T> {
	T convert(byte[] bytes) throws IOException;
}