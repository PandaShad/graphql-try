package com.dgraphproject.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpServiceCustom {
    public HttpResponse<String> makeHttpRequest(String requestBody) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://nameless-brook-470100.eu-central-1.aws.cloud.dgraph.io/graphql"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }
}