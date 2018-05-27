package com.joy.smoothhttp.http;

import android.text.TextUtils;

import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.http.inpututil.ContentLengthInputStream;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.request.url.HttpUrl;
import com.joy.smoothhttp.utils.SLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by joybar on 2018/5/7.
 */

public class HttpUrlConnectionExecutor extends AbstractHttpExecutor {
    private static final int MAXIMUM_REDIRECTS = 5;
    private static final HttpUrlConnectionFactory DEFAULT_CONNECTION_FACTORY = new
            DefaultHttpUrlConnectionFactory();
    private volatile boolean isCancelled;
    private int requestTimes = 0;

    private HttpURLConnection urlConnection;
    private InputStream stream;

    public HttpUrlConnectionExecutor(Request requestOrder) {
        super(requestOrder);
    }

    @Override
    public HttpResult execute(IProgress iProgress) {
        Request request = getRequest();
        HttpResult httpResult = new HttpResult();
        if (requestTimes >= Math.min(request.getRedirects(), MAXIMUM_REDIRECTS)) {
            Exception exception = new Exception("Too many (> " + MAXIMUM_REDIRECTS + ") " +
                    "redirects!");
            httpResult.setThrowable(exception);
            return httpResult;
        }
        if(null!=request.getHeader()){
            Map<String, String> headers = request.getHeader().getHeaders();
            if (null != headers && headers.size() != 0) {
                for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                    urlConnection.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
                }
            }
        }
        try {
            String urlStr = request.getHttpUrl().getUrl();
            urlConnection = DEFAULT_CONNECTION_FACTORY.build(new URL(urlStr));
            urlConnection.setConnectTimeout(request.getTimeOut());
            urlConnection.setReadTimeout(request.getTimeOut());
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (isCancelled) {
                Exception exception = new Exception("has cancelled");
                httpResult.setThrowable(exception);
                return httpResult;
            }
            int responseCode = -100;
            try {
                responseCode = urlConnection.getResponseCode();
            } catch (IOException exception) {
                httpResult.setThrowable(exception);
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                int contentLength = urlConnection.getContentLength();
                httpResult.setContentLength(contentLength);
                iProgress.setTotalLength(contentLength);
                InputStream inputStream = getStreamForSuccessfulRequest(urlConnection);
                httpResult.setInputStream(inputStream);
                byte[] bytes = inputStream2ByteArr(contentLength, inputStream, iProgress);
                httpResult.setBytes(bytes);
                httpResult.setResponseStr(new String(bytes, "UTF-8"));

            } else if (responseCode == 300) {
                String redirectUrlString = urlConnection.getHeaderField("Location");
                if (TextUtils.isEmpty(redirectUrlString)) {
                    Exception exception = new Exception("Received empty or null redirect url");
                    httpResult.setThrowable(exception);
                    return httpResult;
                }
                HttpUrl httpUrl = new HttpUrl.Builder().setUrl(redirectUrlString).build();
                request.SetHttpUrl(httpUrl);
                requestTimes = requestTimes + 1;
                return execute(iProgress);
            }

        } catch (Exception e) {
            e.printStackTrace();
            httpResult.setThrowable(e);
        } finally {
            cleanup();
        }
        return httpResult;

    }

    @Override
    public void cancel() {
        isCancelled = true;
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

    public void cleanup() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                // Ignore
            }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }

    private InputStream getStreamForSuccessfulRequest(HttpURLConnection urlConnection) throws
            IOException {
        if (TextUtils.isEmpty(urlConnection.getContentEncoding())) {
            int contentLength = urlConnection.getContentLength();
            stream = ContentLengthInputStream.obtain(urlConnection.getInputStream(), contentLength);
        } else {
            SLog.printInfo("Got non empty content encoding: " + urlConnection.getContentEncoding());
            stream = urlConnection.getInputStream();
        }
        return stream;
    }

    interface HttpUrlConnectionFactory {
        HttpURLConnection build(URL url) throws IOException;
    }

    private static class DefaultHttpUrlConnectionFactory implements HttpUrlConnectionFactory {
        @Override
        public HttpURLConnection build(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

}
