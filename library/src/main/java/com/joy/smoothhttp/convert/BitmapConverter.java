package com.joy.smoothhttp.convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.joy.smoothhttp.utils.SLog;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/16.
 */

public class BitmapConverter<T> implements Converter<T> {

	@Override
	public T convert(byte[] bytes) throws IOException {
		if (bytes == null) {
			return null;
		}
		BitmapFactory.Options options = new BitmapFactory.Options();
		//设置该属性可以不占用内存，并且能够得到bitmap的宽高等属性，此时得到的bitmap是空
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		//设置计算得到的压缩比例
		options.inSampleSize = 4;
		//设置为false，确保可以得到bitmap != null
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		return (T) bitmap;
	}
}
