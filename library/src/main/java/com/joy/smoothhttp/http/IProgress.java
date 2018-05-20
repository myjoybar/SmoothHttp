package com.joy.smoothhttp.http;

/**
 * Created by joybar on 19/05/2018.
 */

public abstract class IProgress {

   private long totalLength;

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    protected abstract void progressUpdate(long progress);
}
