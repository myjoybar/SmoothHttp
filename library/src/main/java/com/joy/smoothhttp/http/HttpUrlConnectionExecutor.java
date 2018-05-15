package com.joy.smoothhttp.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.joy.smoothhttp.request.Request;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joybar on 2018/5/7.
 */

public class HttpUrlConnectionExecutor<TResult> extends AbstractHttpExecutor {

    public static String TAG = "HttpUrlConnectionExecutor";

    public HttpUrlConnectionExecutor(Request requestOrder) {
        super(requestOrder);
    }


    @Override
    public TResult execute() {
        return execute(null);
    }

    @Override
    public TResult execute(IRequestCallback requestCallback) {

        Bitmap bitmap = null;
        HttpURLConnection con = null;
        Request request = getRequest();
        try {
            String urlStr = request.getHttpUrl().getUrl();
            Log.d(TAG, "url=" + urlStr);
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(3 * 1000);
            con.setReadTimeout(10 * 1000);
            con.setDoInput(true);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        TResult result = (TResult) bitmap;
        return result;
    }
}
