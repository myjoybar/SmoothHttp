package com.joy.http;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joy.http.data.Weather;
import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.call.AbCallback;
import com.joy.smoothhttp.call.Callback;
import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.convert.BitmapConverter;
import com.joy.smoothhttp.convert.GsonConverter;
import com.joy.smoothhttp.convert.StringConverter;
import com.joy.smoothhttp.interceptor.LoggerInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.utils.SLog;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		testConnect1();
//		testConnect2();
		test3();
		testConnect4();
	}



	private void testConnect1(){
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient.Builder().addInterceptor(new LoggerInterceptor()).build();
		final Request request = new Request.Builder()
				//.setHttpUrl("https://www.baidu.com/")
				//.setHttpUrl("https://www.sojson.com/open/api/weather/json.shtml?city=北京/")
				.setHttpUrl("http://www.weather.com.cn/data/cityinfo/101190408.html")
				.build();


		ICall<String> call = smoothHttpClient.newCall(request,new StringConverter<String>());
		call.submit(new Callback<String>() {
			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.print("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, String s) {
				SLog.print("onResponse="+s);
			}

			@Override
			public void onProgressUpdate(ICall call, int values) {

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
		call.submit(new AbCallback<Weather>() {
			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.print("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, Weather weather) {
				SLog.print("onResponse="+weather.toString());
			}
		});

	}

	private void test3(){
//		for(int i = 0;i<1000;i++){
//			testConnect2();
//		}


	}


	private void testConnect4(){

		String url = "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg";

		//SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient.Builder().addInterceptor(new LoggerInterceptor()).build();

		final Request request = new Request.Builder()
				.setHttpUrl(url)
				.build();


		ICall<Bitmap> call = smoothHttpClient.newCall(request,new BitmapConverter());

		call.submit(new AbCallback<Bitmap>() {


			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.print("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, Bitmap bitmap) {
				SLog.print("onResponse="+bitmap.toString());
			}

			@Override
			public void onProgressUpdate(ICall call, int values) {
				super.onProgressUpdate(call, values);
				SLog.print("onProgressUpdate="+values);
			}
		});

	}

}
