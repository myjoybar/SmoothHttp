package com.joy.smoothhttp.interceptor;

import com.joy.smoothhttp.http.HttpFactorySelector;
import com.joy.smoothhttp.http.IProgress;
import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.response.body.ResponseBody;

import java.io.IOException;

/**
 * Created by joybar on 20/05/2018.
 */

public class CallServerInterceptor  implements IInterceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {

        Request request = chain.request();
        HttpResult httpResult = HttpFactorySelector.getInstance().get(request).execute
                (new IProgress() {
                    @Override
                    public void progressUpdate(long progress) {
                        int progressNum = (int) (progress * 1.0f / getTotalLength() * 1000);
                        chain.getAsynchronousTask().publishProgress(progressNum);
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
        response.setResponseTime(System.currentTimeMillis());
        response.setHttpUrl(request.getHttpUrl());
        return response;
    }

}
