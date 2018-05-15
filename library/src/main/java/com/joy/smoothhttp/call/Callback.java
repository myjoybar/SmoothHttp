package com.joy.smoothhttp.call;

import java.io.IOException;

/**
 * Created by joybar on 2018/5/14.
 */

public interface Callback<TResponse> {
	void onFailure(ICall call, IOException e);

	void onResponse(ICall call, TResponse response) ;
}
