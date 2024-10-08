package com.conversorDeMoedas;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.Gson;

public class APIimporter {
    public static MoedaResponse getMoedaResponse(String acronimoMoeda1) throws Exception {

        String apiUrl = "https://v6.exchangerate-api.com/v6/75f188e625607b1eab771ecd/latest/" + acronimoMoeda1;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiUrl))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), MoedaResponse.class);
        } else {
            throw new Exception("Falha ao obter a resposta da API. Código de status: " + response.statusCode());
        }
    }
}
