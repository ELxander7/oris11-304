package ru.kpfu.itis.oris.armanov.http.server;

import ru.kpfu.itis.oris.armanov.http.impl.HttpClient;

import java.util.Map;
import java.util.HashMap;

public class ServletClient {
    public static void main(String[] args) {
        HttpClient servletclient = new HttpClient();

        //GET
        String getURL = "https://gorest.co.in/public/v2/users";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        Map<String, String> params = new HashMap<>();
        params.put("id", "7501213");

        String content = servletclient.get(getURL, headers, params);
        System.out.println(content);

        //POST
        //String postURL = "http://localhost:8080/hello"";
        String postURL = "https://gorest.co.in/public/v2/users";
        Map<String, String> headers2 = new HashMap<>();
        headers2.put("Accept", "application/json");
        headers2.put("Content-Type", "application/json");
        headers2.put("Authorization", "Bearer 60a802c05a2e542a98bc85402a5847c8af0247ee9faa31a0c5167e6f44f2c3aa");
        Map<String, String> postData = new HashMap<>();
        postData.put("name", "Tom Holland");
        postData.put("email", "tomholland@gmail.com");
        postData.put("gender", "male");
        postData.put("status", "active");

        String content2 = servletclient.post(postURL, headers2, postData);
        System.out.println(content2);

        //PUT
        String putURL = "https://gorest.co.in/public/v2/users/7501213";
        Map<String, String> headers3 = new HashMap<>();
        headers3.put("Accept", "application/json");
        headers3.put("Content-Type", "application/json");
        headers3.put("Authorization", "Bearer 60a802c05a2e542a98bc85402a5847c8af0247ee9faa31a0c5167e6f44f2c3aa");
        Map<String, String> putData = new HashMap<>();
        putData.put("name", "Scarlett Johanson");
        putData.put("email", "scarlett@gmail.com");
        putData.put("gender", "female");
        putData.put("status", "active");

        String content3 = servletclient.put(putURL, headers3, putData);
        System.out.println(content3);

        //DELETE
        String deleteURL = "https://gorest.co.in/public/v2/users/7501213";
        Map<String, String> headers4 = new HashMap<>();
        headers4.put("Accept", "application/json");
        headers4.put("Content-Type", "application/json");
        Map<String, String> deleteData = new HashMap<>();
        deleteData.put("name", "Chaturaanan Iyer");
        deleteData.put("email", "iyer_chaturaanan@mcdermott.test");
        deleteData.put("gender", "male");
        deleteData.put("status", "inactive");

        String content4 = servletclient.delete(deleteURL, headers4, deleteData);
        System.out.println(content4);
    }
}
