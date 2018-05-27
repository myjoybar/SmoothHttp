package com.joy.smoothhttp.http.inpututil;

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;

public class FilterInputStream extends InputStream {


    protected volatile InputStream in;


    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }


    @Override
    public void close() throws IOException {
        in.close();
    }


    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }


    @Override
    public boolean markSupported() {
        return in.markSupported();
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        return in.read(buffer, byteOffset, byteCount);
    }


    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }


    @Override
    public long skip(long byteCount) throws IOException {
        return in.skip(byteCount);
    }
}

