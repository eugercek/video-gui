package com.umut.videostream.model.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class NetworkOperations {
    public static String downloadJsonString(String urlString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (!isConnectionSuccessful(responseCode)) {
            throw new IOException("HttpResponseCode: " + responseCode);
        } else {

            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            //Close the scanner
            scanner.close();
            return inline.toString();
        }
    }

    public static Image downloadImage(String urlString) throws IOException {
        URL url = new URL(urlString);
        return ImageIO.read(url);
    }

    @Deprecated
    public static void postJsonString(String urlString, String jsonString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();

        if (!isConnectionSuccessful(responseCode)) {
            throw new IOException("HTTPResponseCode: " + responseCode);
        }

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
        outputStream.close();

        // https://www.youtube.com/watch?v=hWT5WjWqJzc
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        if (!isConnectionSuccessful(connection.getResponseCode())) {
            throw new IOException("HTTPResponseCode: " + responseCode);
        }

    }

    // https://stackoverflow.com/questions/7181534/http-post-using-json-in-java
    // https://stackoverflow.com/questions/1932321/where-can-i-download-the-jar-for-org-apache-http-package
    public static void postJson(String urlString, String jsonString) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(urlString);
        StringEntity postingString = null;
        try {
            postingString = new StringEntity(jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse  response = httpClient.execute(post);

    }

    // HTTP 2xx is success
    private static boolean isConnectionSuccessful(int responseCode) {
        return 2 == responseCode / 100;
    }
}
