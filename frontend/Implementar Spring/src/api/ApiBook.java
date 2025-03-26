/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import model.bean.Book;
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
public class ApiBook {

    private OkHttpClient cliente = new OkHttpClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Book> fetchBooks() {
        try {
            // URL da API
            String url = "http://localhost:8080/books";  // Altere para a URL da sua API

            // Criando uma requisição GET
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + User.getToken())
                    .build();

            // Fazendo a requisição e pegando a resposta
            try (Response response = cliente.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Request failed. Response Code: " + response.code());
                }

                // Lê a resposta como String
                String responseBody = response.body().string();

                // Converte a resposta JSON em uma lista de livros
                ObjectMapper objectMapper = new ObjectMapper();
                List<Book> books = objectMapper.readValue(responseBody, new TypeReference<List<Book>>() {
                });
                System.out.println("List size:> " + books.size());
                return books;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createBook(Book book) {
        try {
            String url = "http://localhost:8080/books";  // Altere para a URL da sua API

            String json = objectMapper.writeValueAsString(book);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

            // Criando uma requisição Post
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + User.getToken())
                    .post(body)
                    .build();

            try (Response response = cliente.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Request failed. Response Code: " + response.code());
                }

                // Se necessário, você pode processar a resposta aqui
                System.out.println("Book created successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void updateBook(Book book) {
        try {
            String url = "http://localhost:8080/books/" + book.getId();  // Altere para a URL da sua API

            String json = objectMapper.writeValueAsString(book);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

            // Criando uma requisição Post
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + User.getToken())
                    .put(body)
                    .build();

            try (Response response = cliente.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("Request failed. Response Code: " + response.code());
                }

                // Se necessário, você pode processar a resposta aqui
                System.out.println("Book updated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            String url = "http://localhost:8080/books/" + id;
            // Criando uma requisição Post
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + User.getToken())
                    .delete()
                    .build();

            try (Response response = cliente.newCall(request).execute()) {
                if (response.code() == 204) {
                    System.out.println("Book deleted successfully.");
                } else {
                    System.out.println("Unexpected response code: " + response.code());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
