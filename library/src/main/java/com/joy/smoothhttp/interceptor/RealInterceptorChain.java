package com.joy.smoothhttp.interceptor;

import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;

import java.io.IOException;
import java.util.List;

import me.joy.async.lib.task.AsynchronousTask;


/**
 * Created by joybar on 20/05/2018.
 */

public class RealInterceptorChain implements IInterceptor.Chain {
    private final List<IInterceptor> interceptors;
    private final Request request;
    private final int index;
    private final AsynchronousTask<Integer, Response> asynchronousTask;

    public RealInterceptorChain(Request request, AsynchronousTask<Integer, Response>
            asynchronousTask, List<IInterceptor> interceptors, int index) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
        this.asynchronousTask = asynchronousTask;
    }

    @Override
    public Request request() {
        return request;
    }

    @Override
    public Response proceed(Request request) throws IOException {
        return realProceed(request);
    }

    @Override
    public AsynchronousTask<Integer, Response> getAsynchronousTask() {
        return asynchronousTask;
    }

    public Response realProceed(Request request) throws IOException {
        if (index >= interceptors.size()) {
            throw new AssertionError();
        }
        // Call the next interceptor in the chain.
        RealInterceptorChain next = new RealInterceptorChain(request, asynchronousTask,
                interceptors, index + 1);
        IInterceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(next);
        // Confirm that the intercepted response isn't null.
        if (response == null) {
            throw new NullPointerException("interceptor " + interceptor + " returned null");
        }
        return response;
    }
}
