package com.dgraphproject.services;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.dgraphproject.entity.Card;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CardService {
    
    private HttpServiceCustom _httpServiceCustom;

    public CardService(HttpServiceCustom httpServiceCustom) {
        this._httpServiceCustom = httpServiceCustom;
    }

    public Card[] getAllCards() throws IOException, InterruptedException {
        String query = "query MyQuery { queryCard { id name description health strenght cost cardType } }";

        String requestBody = "{\"query\":\"" + query + "\"}";

        HttpResponse<String> response = _httpServiceCustom.makeHttpRequest(requestBody);

        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

        JsonArray queryCardArray = jsonObject.getAsJsonObject("data").getAsJsonArray("queryCard");

        Card[] cards = new Gson().fromJson(queryCardArray, Card[].class);

        for (Card card : cards) {
            System.out.println(card);
        }

        return cards;
    }

    public void createCard(String name, String description, int cost, int health, int strenght, String cardType) throws IOException, InterruptedException {
        Card card = new Card();
        card.setName(name);
        card.setDescription(description);
        card.setCost(cost);
        card.setHealth(health);
        card.setCardType(cardType);
        card.setStrength(strenght);
        

        String mutation = "mutation MyMutation($name: String!, $description: String!, $cardType: String!, $cost: Int!, $health: $Int!, $strenght: $Int!) { addCard(input: {name: $name, description: $description, logo: $logo}) { numUids }}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"name\":\"" + name + "\",\"description\":\"" + description + "\",\"cost\":\"" + cost  + "\",\"health\":\"" + health + "\",\"strenght\":\"" + strenght + "\",\"cardType\":\"" + cardType + "\"}}";

        HttpResponse<String> response = _httpServiceCustom.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void updateCard(String id, String name, String description, int cost, int health, int strenght, String cardType) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!], $name: String!, $description: String!, $logo: String!) { updateCard(input: {filter: {id: $id}, set: {description: $description, logo: $logo, name: $name}}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"description\":\"" + description + "\",\"cost\":\"" + cost  + "\",\"health\":\"" + health + "\",\"strenght\":\"" + strenght + "\",\"cardType\":\"" + cardType + "\"}}";

        HttpResponse<String> response = _httpServiceCustom.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void deleteCards(String id) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!]) { deleteCard(filter: {id: $id}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\"}}";

        HttpResponse<String> response = _httpServiceCustom.makeHttpRequest(requestBody);
    }

    public void deleteAllCards() throws IOException, InterruptedException {
        String mutation = "mutation MyMutation { deleteCard(filter: {}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\"}";

        HttpResponse<String> response = _httpServiceCustom.makeHttpRequest(requestBody);
    }
}
