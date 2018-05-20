package com.joy.smoothhttp.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by joybar on 2018/5/14.
 */

public class Utils {
	/** Returns an immutable copy of {@code list}. */
	public static <T> List<T> immutableList(List<T> list) {
		return Collections.unmodifiableList(new ArrayList<>(list));
	}
}
