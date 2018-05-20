package com.joy.smoothhttp.interceptor;

import com.joy.smoothhttp.interceptor.interfaces.IInterceptor;
import com.joy.smoothhttp.response.Response;

import java.io.IOException;

/**
 * Created by joybar on 20/05/2018.
 */

public class CallServerInterceptor  implements IInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
