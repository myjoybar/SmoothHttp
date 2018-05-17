package com.joy.smoothhttp.response.body;

import java.io.InputStream;

/**
 * Created by joybar on 2018/5/16.
 */

public class ResponseBody {

	//	String string;
//	byte[] bytes;
//	Reader charStream;
//	Bitmap bitmap;
	InputStream byteStream;

	public InputStream getByteStream() {
		return byteStream;
	}

	public void setByteStream(InputStream byteStream) {
		this.byteStream = byteStream;
	}


}
