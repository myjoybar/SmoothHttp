package com.joy.smoothhttp;

import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.call.RealCall;
import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 2018/5/14.
 */

public class SmoothHttpClient implements ICall.Factory {

	@Override
	public ICall newCall(Request request) {
		return new RealCall(this, request);
	}
}
