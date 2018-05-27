package com.joy.smoothhttp;

import com.joy.smoothhttp.call.ICall;
import com.joy.smoothhttp.call.RealCall;
import com.joy.smoothhttp.convert.Converter;
import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by joybar on 2018/5/14.
 */

public class SmoothHttpClient implements ICall.Factory {
    final List<IInterceptor> interceptors;

    @Override
    public ICall newCall(Request request, Converter converter) {
        return new RealCall(this, request, converter);
    }

    public SmoothHttpClient() {
        this(new Builder());
    }

    SmoothHttpClient(Builder builder) {
        this.interceptors = Utils.immutableList(builder.interceptors);
    }

    public List<IInterceptor> interceptors() {
        return interceptors;
    }

    public static final class Builder {

        final List<IInterceptor> interceptors = new ArrayList<>();
        public Builder() {
        }

        public Builder addInterceptor(IInterceptor interceptor) {
            interceptors.add(interceptor);
            return this;
        }
        public SmoothHttpClient build() {
            return new SmoothHttpClient(this);
        }

    }
}

