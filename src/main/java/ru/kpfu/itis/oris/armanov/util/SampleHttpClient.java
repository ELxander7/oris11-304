package ru.kpfu.itis.oris.armanov.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.net.URL;

public class SampleHttpClient {
    private static final String URL_GET = "https://jsonplaceholder.typicode.com/users/7";
    private static final String URL_POST = "https://jsonplaceholder.typicode.com/users";
    private static final String URL_PUT = "https://jsonplaceholder.typicode.com/users/7";
    private static final String URL_DELETE = "https://jsonplaceholder.typicode.com/users/7";

    private static final String JSON_INPUT = "{\"name\": \"Tony Stark\",\"email\": \"stark@gmai.com\",\"gender\": \"male\",\"status\": \"active\"}";
    private static final String JSON_UPDATE_INPUT = "{\"name\": \"Updated name\",\"email\": \"UpdatedEmail@internet.ru\",\"gender\": \"male\",\"status\": \"active\"}";

    public static void main(String[] args) {

         //GET
        System.out.println("GET Method: ");
        try{
            doGetMethod(new URL(URL_GET));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("---");

        //POST
        System.out.println("POST Method: ");
        try{
            doPostMethod(new URL(URL_POST), JSON_INPUT);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("---");


        //PUT
        System.out.println("PUT Method: ");
        try {
            doPutMethod(new URL(URL_PUT), JSON_UPDATE_INPUT);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("---");

        //DELETE
        System.out.println("DELETE Method: ");
        try{
            doDeleteMethod(new URL(URL_DELETE));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("---");
    }

    private static void doGetMethod(URL url) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        System.out.println("Статус: " + connection.getResponseCode());
        System.out.println(readResponse(connection));
        connection.disconnect();

    }


    private static void doPostMethod(URL url, String jsonInput) throws IOException {
        HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setRequestProperty("Accept", "application/json");
        postConnection.setRequestProperty("Authorization", "Bearer 60a802c05a2e542a98bc85402a5847c8af0247ee9faa31a0c5167e6f44f2c3aa");
        postConnection.setDoOutput(true);

        try (OutputStream outputStream = postConnection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        System.out.println("Статус: " + postConnection.getResponseCode());
        System.out.println(readResponse(postConnection));

        postConnection.disconnect();
    }

    private static void doPutMethod(URL url, String jsonInput) throws IOException {
        HttpURLConnection putConnection = (HttpURLConnection) url.openConnection();
        putConnection.setRequestMethod("PUT");
        putConnection.setRequestProperty("Content-Type", "application/json");
        putConnection.setRequestProperty("Accept", "application/json");
        putConnection.setRequestProperty("Authorization", "Bearer 60a802c05a2e542a98bc85402a5847c8af0247ee9faa31a0c5167e6f44f2c3aa");
        putConnection.setDoOutput(true);
        try (OutputStream outputStream = putConnection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        System.out.println("Статус: " + putConnection.getResponseCode());
        System.out.println(readResponse(putConnection));
        putConnection.disconnect();
    }

    private static void doDeleteMethod(URL url) throws IOException {
        HttpURLConnection deleteConnection = (HttpURLConnection) url.openConnection();
        deleteConnection.setRequestMethod("DELETE");
        deleteConnection.setRequestProperty("Authorization", "Bearer 0b916082416fa95fa368a2019daba89c237a142d7e76a04c1c2871a84d8cd08a");
        deleteConnection.setDoOutput(true);
        System.out.println("Статус: " + deleteConnection.getResponseCode());
        System.out.println(readResponse(deleteConnection));
        deleteConnection.disconnect();
    }
    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }
}
