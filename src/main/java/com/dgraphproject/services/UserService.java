package com.dgraphproject.services;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.dgraphproject.entity.Achievement;
import com.dgraphproject.entity.Card;
import com.dgraphproject.entity.Deck;
import com.dgraphproject.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserService {
    private HttpServiceCustom _httpService;

    public UserService(HttpServiceCustom httpServiceCustom) {
        this._httpService = httpServiceCustom;
    }
    
    public User[] getAllUsers() throws IOException, InterruptedException {
        String query = "query MyQuery { queryUser { description id logo name } }";

        String requestBody = "{\"query\":\"" + query + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

        JsonArray queryUserArray = jsonObject.getAsJsonObject("data").getAsJsonArray("queryUser");

        User[] users = new Gson().fromJson(queryUserArray, User[].class);

        for (User User : users) {
            System.out.println(User);
        }

        return users;
    }

    public void createUser(String password, int elo, int level, Card[] cardCollection, Deck[] deckCollection, Achievement[] successedAchievement) throws IOException, InterruptedException {
        User user = new User();
        user.setPassword(password);
        user.setElo(elo);
        user.setLevel(level);
        user.setCardCollection(cardCollection);
        user.setDeckCollection(deckCollection);
        user.setSuccessedAchievement(successedAchievement);

        String mutation = "mutation MyMutation($name: String!, $description: String!, $logo: String!) { addUser(input: {name: $name, description: $description, logo: $logo}) { numUids }}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"password\":\"" + password + "\",\"elo\":\"" + elo + "\",\"level\":\"" + level + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        System.out.println(response.body());


        // Transaction txn = dgraphClient.newTransaction();

        // try {

        //     Gson gson = new Gson();
        //     String json = gson.toJson(User);

        //     Mutation mutation = Mutation.newBuilder() 
        //       .setSetJson(ByteString.copyFromUtf8(json.toString()))
        //       .build();

        //     txn.mutate(mutation);
        //     txn.commit();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     txn.discard();
        // }
    }

    public void updateUser(String id, String name, String description, String logo) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!], $name: String!, $description: String!, $logo: String!) { updateUser(input: {filter: {id: $id}, set: {description: $description, logo: $logo, name: $name}}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"description\":\"" + description + "\",\"logo\":\"" + logo + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void deleteUsers(String id) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!]) { deleteUser(filter: {id: $id}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }

    public void deleteAllUsers() throws IOException, InterruptedException {
        String mutation = "mutation MyMutation { deleteUser(filter: {}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }
}
