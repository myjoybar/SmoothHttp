package com.joy.smoothhttp.interceptor;

import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;
import com.joy.smoothhttp.utils.SLog;

import java.io.IOException;
import java.util.List;


/**
 * Created by joybar on 20/05/2018.
 */

public class RealInterceptorChain implements IInterceptor.Chain {
    private final List<IInterceptor> interceptors;
    private final Request request;
    private final int index;

    public RealInterceptorChain(List<IInterceptor> interceptors, int index, Request request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }


    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        return realProceed(request);
    }

    public Response realProceed(Request request) throws IOException {

        SLog.print("index="+index);
        if (index >= interceptors.size()) {
            throw new AssertionError();
        }
        // Call the next interceptor in the chain.
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request);
        IInterceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(next);
        // Confirm that the intercepted response isn't null.
        if (response == null) {
            throw new NullPointerException("interceptor " + interceptor + " returned null");
        }
        return response;
    }
}
