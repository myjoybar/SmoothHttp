package com.joy.smoothhttp.interceptor;

import android.util.LruCache;

import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.response.Response;

import java.io.IOException;

/**
 * Created by joybar on 19/05/2018.
 */

public class CacheInterceptor implements IInterceptor {

    private static LruCache<String, Response> mMemoryCache;
    public static long CACHE_TIME = 5 * 60 * 1000;

    public CacheInterceptor() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Response>(cacheSize);
    }

    public void addToMemoryCache(String key, Response response) {
        if (getFromMemoryCache(key) == null) {
            mMemoryCache.put(key, response);
        }
    }

    public Response getFromMemoryCache(String key) {
        Response response = mMemoryCache.get(key);
        if (null == response) {
            return null;
        }
        if ((System.currentTimeMillis() - response.getResponseTime() > CACHE_TIME)) {
            return null;
        }
        return response;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.getHttpUrl().getUrl();

        Response cache = getFromMemoryCache(url);
        if (cache != null) {
            return cache;
        }
        Response response = chain.proceed(chain.request());
        addToMemoryCache(url, response);
        return response;

    }
}
