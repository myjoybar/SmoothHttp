package com.joy.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.call.Callback;
import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.request.Request;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testConnect();
	}

	private void testConnect(){
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		final Request request = new Request.Builder()
				//.setHttpUrl("https://www.baidu.com/")
				//.setHttpUrl("https://www.sojson.com/open/api/weather/json.shtml?city=北京/")
				.setHttpUrl("http://www.weather.com.cn/data/cityinfo/101190408.html")
				.build();



		ICall<String> call = smoothHttpClient.newCall(request);
		call.submit(new Callback<String>() {

			@Override
			public void onFailure(ICall call, Throwable throwable) {
				Log.d(TAG,"onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, String s) {
				Log.d(TAG,"onResponse="+s);
			}
		});
	}
}
