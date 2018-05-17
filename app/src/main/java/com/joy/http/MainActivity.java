package com.joy.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.joy.http.data.Weather;
import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.call.Callback;
import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.convert.GsonConverter;
import com.joy.smoothhttp.convert.StringConverter;
import com.joy.smoothhttp.request.Request;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testConnect1();
		//testConnect2();
		test3();
	}

	private void testConnect1(){
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		final Request request = new Request.Builder()
				//.setHttpUrl("https://www.baidu.com/")
				//.setHttpUrl("https://www.sojson.com/open/api/weather/json.shtml?city=北京/")
				.setHttpUrl("http://www.weather.com.cn/data/cityinfo/101190408.html")
				.build();


		ICall<String> call = smoothHttpClient.newCall(request,new StringConverter<String>());
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

	private void testConnect2(){
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		final Request request = new Request.Builder()
				//.setHttpUrl("https://www.baidu.com/")
				//.setHttpUrl("https://www.sojson.com/open/api/weather/json.shtml?city=北京/")
				.setHttpUrl("http://www.weather.com.cn/data/cityinfo/101190408.html")
				.build();


		ICall<Weather> call = smoothHttpClient.newCall(request,new GsonConverter<Weather>(Weather.class));
		call.submit(new Callback<Weather>() {
			@Override
			public void onFailure(ICall call, Throwable throwable) {
				Log.d(TAG,"onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, Weather weather) {
				Log.d(TAG,"onResponse="+weather.toString());
			}
		});

	}

	private void test3(){
		for(int i = 0;i<1000;i++){
			testConnect2();
		}
	}

}
