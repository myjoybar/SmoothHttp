package com.joy.smoothhttp.response.body;

import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public class ResponseBody {

	String string;
//	Reader charStream;
//	Bitmap bitmap;
	InputStream byteStream;
	byte[] bytes;

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public InputStream getByteStream() {
		return byteStream;
	}

	public void setByteStream(InputStream byteStream) {
		this.byteStream = byteStream;
	}
}
