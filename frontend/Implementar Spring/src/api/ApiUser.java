/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import model.bean.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Senai
 */
public class ApiUser {

    private static final String url = "http://localhost:8080/users";

    private final OkHttpClient client = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String post(String json) throws IOException {
        RequestBody body = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .url(url + "/login")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getUserRole(String login) {
        try {
            Request request = new Request.Builder()
                    .url(url + "/" + login) // Supondo que a API retorna o usuário por login
                    .get()
                    .addHeader("Authorization", "Bearer " + User.getToken()) // Se necessário
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Request failed. Response Code: " + response.code());
                }

                // Processa a resposta JSON
                String responseBody = response.body().string();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                return jsonNode.get("role").asText(); // Retorna "admin" ou "user"
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "sem nada"; // Em caso de erro
        }
    }
}
