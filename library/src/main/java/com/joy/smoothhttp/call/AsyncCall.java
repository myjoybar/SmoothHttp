package com.joy.smoothhttp.call;

import android.util.Log;

import com.joy.smoothhttp.http.HttpFactorySelector;
import com.joy.smoothhttp.request.Request;

import me.joy.async.lib.task.AsynchronousTask;

/**
 * Created by joybar on 2018/5/15.
 */

public class AsyncCall<TResponse> {
	Request request;
	Callback<TResponse> callback;
	AsynchronousTask asynchronousTask;
	ICall iCall;


	public AsyncCall(final ICall iCall, final Request request, final Callback<TResponse> callback) {
		this.iCall = iCall;
		this.callback = callback;
		asynchronousTask = new AsynchronousTask<Void, TResponse>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected TResponse doInBackground() {
				Log.d("MainActivity","doInBackground");
				TResponse result = (TResponse) HttpFactorySelector.getInstance().get(request).execute();
				return result;
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
				Log.d("MainActivity","onProgressUpdate");
			}

			@Override
			protected void onPostExecute(TResponse tResponse) {
				super.onPostExecute(tResponse);
				callback.onResponse(iCall,tResponse);
				Log.d("MainActivity","onPostExecute");
			}
		};
	}

	public void execute() {
		asynchronousTask.execute();
	}


//	private TResponse getResponseWithInterceptorChain() {
//
//	}

}
