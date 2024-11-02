package ru.kpfu.itis.oris.armanov.http.impl;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.net.URL;

public class HttpClient implements ru.kpfu.itis.oris.armanov.http.interfaces.HttpClient {


    @Override
    public String get( String url,Map<String, String> headers, Map<String, String> params) {
        String content;
        try{
            if (url.endsWith("/")){
                url = addParamsForUsers(url, params);
            }
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");

            for (String key : headers.keySet()){
                connection.setRequestProperty(key, headers.get(key));
            }

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            content = readResponse(connection);
            System.out.println("Status: " + connection.getResponseCode());

            connection.disconnect();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return content;
    }

    @Override
    public String post( String url,Map<String, String> headers, Map<String, String> data) {
        String content;
        try{
            URL connectionUrl = new URL(url);
            HttpURLConnection postconnection = (HttpURLConnection) connectionUrl.openConnection();
            postconnection.setRequestMethod("POST");
            postconnection.setDoOutput(true);

            for (String key : headers.keySet()){
                postconnection.setRequestProperty(key, headers.get(key));
            }

            ObjectMapper mapper = new ObjectMapper();
            String mapInput = mapper.writeValueAsString(data);

            try (OutputStream outputStream = postconnection.getOutputStream()) {
                byte[] input = mapInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println("Status: " + postconnection.getResponseCode());

            content = readResponse(postconnection);

            postconnection.disconnect();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return content;
    }

    @Override
    public String put( String url,Map<String, String> headers, Map<String, String> data) {
        String content;
        try{
            URL connectionUrl = new URL(url);
            HttpURLConnection putconnection = (HttpURLConnection) connectionUrl.openConnection();
            putconnection.setRequestMethod("PUT");
            putconnection.setDoOutput(true);

            for (String key : headers.keySet()){
                putconnection.setRequestProperty(key, headers.get(key));
            }

            ObjectMapper mapper = new ObjectMapper();
            String mapInput = mapper.writeValueAsString(data);


            try (OutputStream outputStream = putconnection.getOutputStream()) {
                byte[] input = mapInput.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println("Status: " + putconnection.getResponseCode());

            content = readResponse(putconnection);

            putconnection.disconnect();

        } catch (IOException e){
            throw new RuntimeException(e);
        } return content;
    }

    @Override
    public String delete( String url,Map<String, String> headers, Map<String, String> data) {
        String content;
        try{
            URL connectionUrl = new URL(url);
            HttpURLConnection deleteconnection = (HttpURLConnection) connectionUrl.openConnection();
            deleteconnection.setRequestMethod("DELETE");
            deleteconnection.setDoOutput(true);

            for(String key : headers.keySet()){
                deleteconnection.setRequestProperty(key, headers.get(key));
            }

            System.out.println("Status: " + deleteconnection.getResponseCode());

            content = readResponse(deleteconnection);

            deleteconnection.disconnect();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return content;
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

    private static String addParams(String url, Map<String, String> params) {
        StringBuilder newUrl = new StringBuilder(url);

        boolean firstParam = true;

        for (String key : params.keySet()) {
            if (firstParam) {
                newUrl.append(key).append("=").append(params.get(key));
                firstParam = false;
            } else {
                newUrl.append("&").append(key).append("=").append(params.get(key));
            }
        }

        return newUrl.toString();
    }



    private static String addParamsForUsers(String url, Map<String, String> params) {
        StringBuilder newUrl = new StringBuilder(url);

        for (String key: params.keySet()){
            newUrl.append(params.get(key));
        }
        return newUrl.toString();
    }

}
