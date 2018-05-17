package com.joy.smoothhttp.call.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public class InputSteamUtils {

	public static String toString(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[2048];
		int readBytes = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			stringBuilder.append(new String(buffer, 0, readBytes));
		}
		return stringBuilder.toString();

	}

	public static Bitmap toBitmap(InputStream inputStream)  {
		return BitmapFactory.decodeStream(inputStream);
	}
	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}

}
