package com.joy.smoothhttp.http;

import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.request.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joybar on 2018/5/7.
 */

public class HttpUrlConnectionExecutor extends AbstractHttpExecutor {


    public HttpUrlConnectionExecutor(Request requestOrder) {
        super(requestOrder);
    }

    @Override
    public HttpResult execute(IProgress iProgress) {
        HttpResult httpResult = new HttpResult();
        HttpURLConnection con = null;
        Request request = getRequest();
        try {
            String urlStr = request.getHttpUrl().getUrl();
            URL url = new URL(urlStr);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(request.getTimeOut());
            con.setReadTimeout(request.getTimeOut());
            con.setDoInput(true);
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                int contentLength = con.getContentLength();
                httpResult.setContentLength(contentLength);
                iProgress.setTotalLength(contentLength);
                byte[] bytes = inputStream2ByteArr(contentLength, con.getInputStream(), iProgress);
                httpResult.setBytes(bytes);
                httpResult.setResponseStr(new String(bytes, "UTF-8"));

            }

        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setThrowable(e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return httpResult;

    }

    @Override
    public HttpResult execute() {
        return execute(null);
    }

    private static byte[] inputStream2ByteArr(int contentLength, InputStream inputStream,
                                              IProgress iProgress) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        long sum = 0;
        while ((len = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
            if (null != iProgress) {
                sum += len;
                iProgress.progressUpdate(sum);
            }
        }
        inputStream.close();
        outputStream.close();
        return outputStream.toByteArray();
    }


}
