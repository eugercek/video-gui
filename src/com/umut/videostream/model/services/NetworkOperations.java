package com.umut.videostream.model.services;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkOperations {
    public static String downloadJsonString(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (isConnectionSuccessful(responseCode)) {
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

    // HTTP 2xx is success
    private static boolean isConnectionSuccessful(int responseCode){
        return 2 == responseCode / 100;
    }
}
