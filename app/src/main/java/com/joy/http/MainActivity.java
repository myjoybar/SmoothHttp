package com.joy.http;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joy.http.data.Weather;
import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.call.AbCallback;
import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.convert.BitmapConverter;
import com.joy.smoothhttp.convert.GsonConverter;
import com.joy.smoothhttp.convert.StringConverter;
import com.joy.smoothhttp.interceptor.CacheInterceptor;
import com.joy.smoothhttp.interceptor.LoggerInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.utils.SLog;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	testConnect1();
//		testConnect2();
		//	test3();
		//testConnect4();

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				testConnect4();
			}
		});
	}


	SmoothHttpClient smoothHttpClient = new SmoothHttpClient.Builder().addInterceptor(new LoggerInterceptor()).build();

	private void testConnect1(){
		String url = "http://www.weather.com.cn/data/cityinfo/101190408.html";
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		final Request request = new Request.Builder()
				.setHttpUrl(url)
				.build();
		ICall<String> call = smoothHttpClient.newCall(request,new StringConverter<String>());
		call.submit(new AbCallback<String>() {
			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.printInfo("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, String s) {
				SLog.printInfo("onResponse="+s);
			}

		});

	}

	private void testConnect2(){

		SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
		String url = "http://www.weather.com.cn/data/cityinfo/101190408.html";
		final Request request = new Request.Builder()
				.setHttpUrl(url)
				.build();
		ICall<Weather> call = smoothHttpClient.newCall(request,new GsonConverter<Weather>(Weather.class));
		call.submit(new AbCallback<Weather>() {
			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.printInfo("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, Weather weather) {
				SLog.printInfo("onResponse="+weather.toString());
			}
		});

	}

	private void test3(){
		for(int i = 0;i<1000;i++){
			testConnect1();
		}


	}


	private void test4before(){

	}

	CacheInterceptor cacheInterceptor = new CacheInterceptor();
	private void testConnect4(){

		String url = "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg";
		SmoothHttpClient smoothHttpClient = new SmoothHttpClient.Builder()
				.addInterceptor(new LoggerInterceptor())
				.addInterceptor(cacheInterceptor)
				.build();
		final Request request = new Request.Builder()
				.setHttpUrl(url)
				.build();

		final ICall<Bitmap> call = smoothHttpClient.newCall(request,new BitmapConverter());
		call.submit(new AbCallback<Bitmap>() {

			@Override
			public void onFailure(ICall call, Throwable throwable) {
				SLog.printInfo("onFailure="+throwable.getMessage());
			}

			@Override
			public void onResponse(ICall call, Bitmap bitmap) {
				SLog.printInfo("onResponse="+bitmap.toString());
			}

			@Override
			public void onProgressUpdate(ICall call, int values) {
				super.onProgressUpdate(call, values);
				SLog.printInfo("onProgressUpdate="+values);
			}
		});

	}

}
