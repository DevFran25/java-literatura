package com.challenge.literatura.cliente;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GuntendexCliente {

    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarLibroPorTitulo(String titulo) throws Exception {
        String baseUrl = "https://gutendex.com/books/";
        String url = baseUrl + "?search=" + titulo.replace(" ", "%20");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
