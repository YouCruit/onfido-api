package com.youcruit.onfido.api.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public interface URLConnectionFactory {
    HttpURLConnection create(URL url) throws IOException;
}
