package com.joy.smoothhttp.call;

import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.http.HttpFactorySelector;
import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.response.body.ResponseBody;
import com.joy.smoothhttp.utils.SLog;

import java.io.IOException;
import java.io.InputStream;

import me.joy.async.lib.task.AsynchronousTask;

/**
 * Created by joybar on 2018/5/15.
 */

public class AsyncCall<TResponse> {
	private Callback<TResponse> callback;
	private AsynchronousTask asynchronousTask;
	private ICall iCall;


	public AsyncCall(final ICall iCall, final Request request, final Converter converter, final Callback<TResponse> callback) {
		this.iCall = iCall;
		this.callback = callback;
		asynchronousTask = new AsynchronousTask<Void, Response>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Response doInBackground() {
				SLog.print("doInBackground");
				HttpResult httpResult = HttpFactorySelector.getInstance().get(request).execute();
				Response response = new Response();
				if (httpResult.getThrowable() != null) {
					response.setThrowable(httpResult.getThrowable());
					return response;
				}
				ResponseBody responseBody = new ResponseBody();
				responseBody.setBytes(httpResult.getBytes());
				response.setResponseBody(responseBody);
				return response;
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				super.onProgressUpdate(values);
				SLog.print("onProgressUpdate");
			}

			@Override
			protected void onPostExecute(Response response) {
				super.onPostExecute(response);
				if (response.getThrowable() != null) {
					SLog.print("callback.onFailure" + response.getThrowable().getMessage());
					callback.onFailure(iCall, response.getThrowable());
				} else {
					try {
						callback.onResponse(iCall, (TResponse) converter.convert(response.getResponseBody().getBytes()));
					} catch (IOException e) {
						e.printStackTrace();
						callback.onFailure(iCall, e);
					}
				}
			}
		};
	}

	public void execute() {
		asynchronousTask.execute();
	}

	public void cancel() {
		if (null != asynchronousTask) {
			asynchronousTask.cancel(false);
		}
	}

//	private TResponse getResponseWithInterceptorChain() {
//
//	}

}
