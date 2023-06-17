package com.dgraphproject.services;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.dgraphproject.entity.Card;
import com.dgraphproject.entity.Deck;
import com.dgraphproject.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DeckService {
    private HttpServiceCustom _httpService;

    public DeckService(HttpServiceCustom httpServiceCustom) {
        this._httpService = httpServiceCustom;
    }
    
    public Deck[] getAllDecks() throws IOException, InterruptedException {
        String query = "query MyQuery { queryDeck { description id logo name } }";

        String requestBody = "{\"query\":\"" + query + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

        JsonArray queryDeckArray = jsonObject.getAsJsonObject("data").getAsJsonArray("queryDeck");

        Deck[] decks = new Gson().fromJson(queryDeckArray, Deck[].class);

        for (Deck Deck : decks) {
            System.out.println(Deck);
        }

        return decks;
    }

    public void createDeck(String name, int maxCardNumberprivate, Card[] cards) throws IOException, InterruptedException {
        Deck Deck = new Deck();
        Deck.setName(name);
        Deck.setMaxCardNumber(maxCardNumberprivate);
        Deck.setCard(cards);

        String mutation = "mutation MyMutation($name: String!, $description: String!, $logo: String!) { addDeck(input: {name: $name, description: $description, logo: $logo}) { numUids }}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"name\":\"" + name + "\",\"maxCardNumber\":\"" + maxCardNumberprivate + "\",\"cards\":\"" + cards + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void deleteDeck(String id) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!]) { deleteDeck(filter: {id: $id}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }

    public void deleteAllDecks() throws IOException, InterruptedException {
        String mutation = "mutation MyMutation { deleteDeck(filter: {}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }
}
