package com.umut.videostream.model.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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

    public static void postJsonString(String urlString, String jsonString) throws MalformedURLException, IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();

        if(!isConnectionSuccessful(responseCode)){
            throw new IOException("HTTPResponseCode: " + responseCode);
        }

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
        outputStream.close();

        // https://www.youtube.com/watch?v=hWT5WjWqJzc
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        if(!isConnectionSuccessful(connection.getResponseCode())){
            throw new IOException("HTTPResponseCode: " + responseCode);
        }

        }

    // HTTP 2xx is success
    private static boolean isConnectionSuccessful(int responseCode){
        return 2 == responseCode / 100;
    }
}
