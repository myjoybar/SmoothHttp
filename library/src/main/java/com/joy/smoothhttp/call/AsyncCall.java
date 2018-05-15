package com.joy.smoothhttp.call;

import android.util.Log;

import com.joy.smoothhttp.http.HttpFactorySelector;
import com.joy.smoothhttp.http.data.BaseResult;
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
		asynchronousTask = new AsynchronousTask<Void, BaseResult>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected BaseResult doInBackground() {
				Log.d("MainActivity","doInBackground");
				BaseResult baseResult = HttpFactorySelector.getInstance().get(request).execute();
				return baseResult;
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
				Log.d("MainActivity","onProgressUpdate");
			}

			@Override
			protected void onPostExecute(BaseResult baseResult) {
				super.onPostExecute(baseResult);
				if(baseResult.getThrowable()!=null){
					Log.d("MainActivity","callback.onFailure"+baseResult.getThrowable().getMessage());
					callback.onFailure(iCall,baseResult.getThrowable());
				}else{
					Log.d("MainActivity","callback.onResponse"+baseResult.getResult());
					callback.onResponse(iCall, (TResponse) baseResult.getResult());

				}

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
