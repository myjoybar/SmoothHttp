package com.joy.smoothhttp.http.data;

/**
 * Created by joybar on 15/05/2018.
 */

public class BaseResult<TResult> {
    TResult tResult;
    Throwable throwable;

    public BaseResult() {
    }


    public TResult getResult() {
        return tResult;
    }

    public void settResult(TResult tResult) {
        this.tResult = tResult;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
