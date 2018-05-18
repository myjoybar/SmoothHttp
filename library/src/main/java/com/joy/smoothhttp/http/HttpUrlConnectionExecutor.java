package com.joy.smoothhttp.http;

import com.joy.smoothhttp.http.data.HttpResult;
import com.joy.smoothhttp.request.Request;
import com.joy.smoothhttp.utils.SLog;

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
	public HttpResult execute() {
		HttpResult httpResult = new HttpResult();
		HttpURLConnection con = null;
		Request request = getRequest();
		try {
			String urlStr = request.getHttpUrl().getUrl();
			SLog.print("url=" + urlStr);
			URL url = new URL(urlStr);
			con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(3 * 1000);
			con.setReadTimeout(request.getTimeOut());
			con.setDoInput(true);
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				httpResult.setBytes(inputStream2ByteArr(con.getInputStream()));
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

	private static byte[] inputStream2ByteArr(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buff)) != -1) {
			outputStream.write(buff, 0, len);
		}
		inputStream.close();
		outputStream.close();
		return outputStream.toByteArray();
	}


}
