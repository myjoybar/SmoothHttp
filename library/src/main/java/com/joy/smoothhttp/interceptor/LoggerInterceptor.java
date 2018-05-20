package com.joy.smoothhttp.interceptor;

import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.utils.SLog;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by joybar on 19/05/2018.
 */

public class LoggerInterceptor implements IInterceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        SLog.print(String.format(Locale.getDefault(), "Received response for %s in %.1fms",
                request.getHttpUrl().getUrl(), (t2 - t1) / 1e6d));
        String content = response.getResponseBody().getString();
        SLog.print("response body:" + content);
        return response;

    }
}
