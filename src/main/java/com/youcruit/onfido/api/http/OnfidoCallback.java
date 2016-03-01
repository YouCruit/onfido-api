package com.youcruit.onfido.api.http;

import java.io.IOException;

public interface OnfidoCallback<V> {
    void onSuccess(V response);
    void onError(IOException cause);
}
