package com.joy.smoothhttp.call;

import com.joy.smoothhttp.SmoothHttpClient;
import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.http.HttpFactorySelector;
import com.joy.smoothhttp.http.IProgress;
import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.interceptor.RealInterceptorChain;
import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.response.body.ResponseBody;
import com.joy.smoothhttp.utils.SLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.joy.async.lib.task.AsynchronousTask;

/**
 * Created by joybar on 2018/5/15.
 */

public class AsyncCall<TResponse> {
    private SmoothHttpClient smoothHttpClient;
    private Callback<TResponse> callback;
    private AsynchronousTask<Integer, Response> asynchronousTask;
    private ICall iCall;
    private Request originalRequest;


    public AsyncCall(SmoothHttpClient smoothHttpClient, final ICall iCall, final Request request,
                     final Converter converter, final Callback<TResponse> callback) {
        this.smoothHttpClient = smoothHttpClient;
        this.iCall = iCall;
        this.callback = callback;
        this.originalRequest = request;
        asynchronousTask = new AsynchronousTask<Integer, Response>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Response doInBackground() {
                SLog.print("doInBackground");
                HttpResult httpResult = HttpFactorySelector.getInstance().get(request).execute
                        (new IProgress() {
                    @Override
                    public void progressUpdate(long progress) {
                        int progressNum = (int) (progress * 1.0f / getTotalLength() * 100);
                        publishProgress(progressNum);
                    }
                });


                Response response = new Response();
                if (httpResult.getThrowable() != null) {
                    response.setThrowable(httpResult.getThrowable());
                    return response;
                }
                ResponseBody responseBody = new ResponseBody();
                responseBody.setBytes(httpResult.getBytes());
                responseBody.setString(httpResult.getResponseStr());
                response.setResponseBody(responseBody);
                return response;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                callback.onProgressUpdate(iCall, values[0]);
            }

            @Override
            protected void onPostExecute(Response response) {
                super.onPostExecute(response);
                if (response.getThrowable() != null) {
                    SLog.print("callback.onFailure" + response.getThrowable().getMessage());
                    callback.onFailure(iCall, response.getThrowable());
                } else {
                    try {
                        callback.onResponse(iCall, (TResponse) converter.convert(response
                                .getResponseBody().getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onFailure(iCall, e);
                    }
                }
            }
        };
    }

    public void execute() {
        try {
            getResponseWithInterceptorChain();
        } catch (IOException e) {
            e.printStackTrace();
        }
        asynchronousTask.execute();
    }

    public void cancel() {
        if (null != asynchronousTask) {
            asynchronousTask.cancel(false);
        }
    }

    private Response getResponseWithInterceptorChain() throws IOException {
        List<IInterceptor> interceptors = new ArrayList<>();
        interceptors.addAll(smoothHttpClient.interceptors());
        IInterceptor.Chain chain = new RealInterceptorChain(interceptors, 0, originalRequest);
        return chain.proceed(originalRequest);
    }

}
