package com.joy.smoothhttp.request.header;

import com.joy.smoothhttp.utils.CheckUtils;
import com.joy.smoothhttp.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by joybar on 2018/5/14.
 */

public class RequestHeader {
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    private Map<String, String> headers;


    public List<String> getEncodedNames() {
        return encodedNames;
    }

    public List<String> getEncodedValues() {
        return encodedValues;
    }

    public Map<String, String> getHeaders() {
        int count = encodedNames.size();
        for (int i = 0; count < count; i++) {
            headers.put(encodedNames.get(i), encodedValues.get(i));
        }
        return headers;
    }

    RequestHeader(Builder builder) {
        this.encodedNames = Utils.immutableList(builder.names);
        this.encodedValues = Utils.immutableList(builder.values);
    }

    public static final class Builder {
        private final List<String> names = new ArrayList<>();
        private final List<String> values = new ArrayList<>();

        public Builder add(String name, String value) {
            CheckUtils.checkNotNull(name);
            CheckUtils.checkNotNull(value);
            names.add(name);
            values.add(value);
            return this;
        }

        public Builder addEncoded(String name, String value) {
            CheckUtils.checkNotNull(name);
            CheckUtils.checkNotNull(value);
            names.add(name);
            values.add(value);
            return this;
        }

        public RequestHeader build() {
            return new RequestHeader(this);
        }
    }
}
