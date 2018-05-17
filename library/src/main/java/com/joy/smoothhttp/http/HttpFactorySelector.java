package com.joy.smoothhttp.http;

import com.joy.smoothhttp.request.Request;

/**
 * Created by joybar on 11/05/2018.
 */

public class HttpFactorySelector{

    public HttpFactorySelector() {

    }

    public static HttpFactorySelector getInstance() {
        return HttpFactorySelectorHolder.INSTANCE;
    }

    private static class HttpFactorySelectorHolder {
        private static HttpFactorySelector INSTANCE = new HttpFactorySelector();
    }

    private AbstractHttpExecutor abstractHttpExecutor;

    public void register(AbstractHttpExecutor abstractLoader) {
        this.abstractHttpExecutor = abstractLoader;
    }

    public AbstractHttpExecutor get(Request request) {

        if (null == this.abstractHttpExecutor) {
            return getDefaultLoader(request);
        }
        return abstractHttpExecutor;
    }

    private AbstractHttpExecutor getDefaultLoader(Request request) {


        AbstractHttpExecutor abstractLoader = new HttpUrlConnectionExecutor(request);
        return abstractLoader;
    }
}
