package com.joy.smoothhttp.http;

import android.graphics.Bitmap;
import android.util.Log;

import com.joy.smoothhttp.http.data.BaseResult;
import com.joy.smoothhttp.request.Request;

import java.io.IOException;
import java.io.InputStream;
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
    public BaseResult execute() {
        return execute(null);
    }

    @Override
    public BaseResult execute(IRequestCallback requestCallback) {
        BaseResult baseResult = new BaseResult();
        TResult tResult = null;
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
                //  bitmap = BitmapFactory.decodeStream(con.getInputStream());
                tResult = (TResult) inputStreamToString(con.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setThrowable(e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        baseResult.settResult(tResult);
        return baseResult;
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[2048];
        int readBytes = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((readBytes = inputStream.read(buffer)) > 0) {
            stringBuilder.append(new String(buffer, 0, readBytes));
        }
        return stringBuilder.toString();

    }
}
