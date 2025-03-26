/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Senai
 */
public class ApiUser{
    private static final String url = "http://localhost:8080/users";
    
    private final OkHttpClient client = new OkHttpClient();
    
    public String post(String json) throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"),json);

        Request request = new Request.Builder()
                .url(url + "/login")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
    
}
