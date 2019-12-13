package com.example.myapplication.services;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest {

    static String getRequest(String urlStr) throws IOException {

        URL url = new URL(urlStr + "?api_key=5ef17c68d28274d819abdd71c6fd31bc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(100000);
        connection.connect();


        int responseCode = connection.getResponseCode();

        InputStream in = new BufferedInputStream(connection.getInputStream());

        return readStream(in);

    }

    private static String readStream(InputStream is) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        int i = is.read();
        while (i != -1) {
            bo.write(i);
            i = is.read();
        }
        return bo.toString();
    }

}
