package ru.kpfu.itis.oris.armanov.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Sample {
    public static void main(String[] args) {

        //get
        try{
            URL url = new URL("https://jsonplaceholder.typicode.com/posts?userId=2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            System.out.println(readResponse(connection));
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        //post
        try {
            URL postUrl = new URL("https://gorest.co.in/public/v2/users");
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer 58762cdab4e248c10d165f6bbe89d18a444dff00267b6cfcec49acf9dceb94b7");
            postConnection.setDoOutput(true);
            String jsonInput = "{\"name\": \"Sen. Anala Iyer\",\"email\": \"dsen_anala_iyer123@stroman-leannon.test\",\"gender\": \"female\",\"status\": \"active\"}";
            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input,0, input.length);
            }
            System.out.println(postConnection.getResponseCode());
            System.out.println(readResponse(postConnection));
            postConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } */




        //put
        try{
            URL putURL = new URL("https://jsonplaceholder.typicode.com/posts/2");
            HttpURLConnection putconnection = (HttpURLConnection) putURL.openConnection();
            putconnection.setRequestMethod("PUT");
            putconnection.setRequestProperty("Content-Type", "application/json");
            putconnection.setRequestProperty("Accept", "application/json");
            putconnection.setDoOutput(true);

            String jsonInput = "{\"userId\": 2, \"id\": 11,\"title\": \"New title for 11\", \"body\": \"New body for 11\"}";

            try (OutputStream outputStream = putconnection.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }
            System.out.println(putconnection.getResponseCode());
            System.out.println(readResponse(putconnection));

            putconnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        //delete
        try{
            URL deleteURL = new URL("https://jsonplaceholder.typicode.com/posts/2");
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteURL.openConnection();

            deleteConnection.setRequestMethod("DELETE");

            System.out.println(deleteConnection.getResponseCode());
            System.out.println(readResponse(deleteConnection));

            deleteConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null){
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }
}