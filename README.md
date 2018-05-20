# SmoothHttp
Smooth Http ，使用方法类似Okhttp
## Features
 - 支持切换http请求方式（HttpURLConnection，HttpClient，Volly，甚至可以切换成OkHttp）

 
 - 支持范型指定Response返回类型：目前支持String，Bitmap以及自定义的实体类，可方便扩展

 
 - 拦截器调用模式，目前已实现LoggerInterceptor，CacheInterceptor，CallServerInterceptor，可方便添加自定义的Interceptor，

 
 - 支持切换异步任务机制，目前用自定义的AsynchronousTask实现（可以自定义线程池），当然也可方便的切换成AsyncTask或者自定义的异步任务器。
 
- 支持进度回调

## Installation
### Gradle Dependency

#####   Add the library to your project build.gradle

```gradle
compile 'com.joybar.smoothhttp:library:1.0.6'
```

## Sample Usage

### 使用1：返回类型为String


```java
String url = "http://www.weather.com.cn/data/cityinfo/101190408.html";
SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
final Request request = new Request.Builder()
		.setHttpUrl(url)
		.build();
ICall<String> call = smoothHttpClient.newCall(request,new StringConverter<String>());
call.submit(new AbCallback<String>() {
	@Override
	public void onFailure(ICall call, Throwable throwable) {
		SLog.print("onFailure="+throwable.getMessage());
	}

	@Override
	public void onResponse(ICall call, String s) {
		SLog.print("onResponse="+s);
	}

});

```


### 使用2：返回类型为自定义实体（Weather）


```java
SmoothHttpClient smoothHttpClient = new SmoothHttpClient();
String url = "http://www.weather.com.cn/data/cityinfo/101190408.html";
final Request request = new Request.Builder()
		.setHttpUrl(url)
		.build();
ICall<Weather> call = smoothHttpClient.newCall(request,new GsonConverter<Weather>(Weather.class));
call.submit(new AbCallback<Weather>() {
	@Override
	public void onFailure(ICall call, Throwable throwable) {
		SLog.print("onFailure="+throwable.getMessage());
	}

	@Override
	public void onResponse(ICall call, Weather weather) {
		SLog.print("onResponse="+weather.toString());
	}
});

```

### 使用3：返回类型为Bitmap，监听进度回调（onProgressUpdate），添加缓存拦截器和日志拦截器


```java
String url = "http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg";
SmoothHttpClient smoothHttpClient = new SmoothHttpClient.Builder()
		.addInterceptor(new LoggerInterceptor())
		.addInterceptor(new CacheInterceptor())
		.build();
final Request request = new Request.Builder()
		.setHttpUrl(url)
		.build();

final ICall<Bitmap> call = smoothHttpClient.newCall(request,new BitmapConverter());
call.submit(new AbCallback<Bitmap>() {

	@Override
	public void onFailure(ICall call, Throwable throwable) {
		SLog.print("onFailure="+throwable.getMessage());
	}

	@Override
	public void onResponse(ICall call, Bitmap bitmap) {
		SLog.print("onResponse="+bitmap.toString());
	}

	@Override
	public void onProgressUpdate(ICall call, int values) {
		super.onProgressUpdate(call, values);
		SLog.print("onProgressUpdate="+values);
	}
});

```

## Extention

### 1 . 添加自定义Converter
只需继承Converter接口，实现convert方法即可，以BitmapConverter为例：


```java
public class BitmapConverter<T> implements Converter<T> {

	@Override
	public T convert(byte[] bytes) throws IOException {
		if (bytes == null) {
			return null;
		}
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		options.inSampleSize = 4;
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		return (T) bitmap;
	}
}

```
### 2 . 添加自定义拦截器
只需继承IInterceptor接口，实现intercept方法即可，和Okhttp完全一样，以LoggerInterceptor为例：


```java
public class LoggerInterceptor implements IInterceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        SLog.print(String.format(Locale.getDefault(), "Received response for %s in %.1fms",
                request.getHttpUrl().getUrl(), (t2 - t1) / 1e6d));
        String content = response.getResponseBody().getString();
        SLog.print("response body:" + content);
        return response;
    }
}


```

#### 更多扩展功能添加完善中……
## License

    Copyright 2018 MyJoybar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.   