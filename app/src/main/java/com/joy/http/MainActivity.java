package com.joy.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.call.Callback;
import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.request.Request;

import java.io.IOException;

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
				.setHttpUrl("https://www.baidu.com/")
				.build();

		ICall<String> call = smoothHttpClient.newCall(request);
		call.submit(new Callback<String>() {
			@Override
			public void onFailure(ICall call, IOException e) {
				Log.d(TAG,"onFailure");
			}

			@Override
			public void onResponse(ICall call, String s)  {
				Log.d(TAG,"onResponse="+s);

			}
		});
	}
}
