package com.dgraphproject.services;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.dgraphproject.entity.Achievement;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AchivementService {

    private HttpServiceCustom _httpService;

    public AchivementService(HttpServiceCustom httpServiceCustom) {
        this._httpService = httpServiceCustom;
    }
    
    public Achievement[] getAllAchievements() throws IOException, InterruptedException {
        String query = "query MyQuery { queryAchievement { description id logo name } }";

        String requestBody = "{\"query\":\"" + query + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

        JsonArray queryAchievementArray = jsonObject.getAsJsonObject("data").getAsJsonArray("queryAchievement");

        Achievement[] achievements = new Gson().fromJson(queryAchievementArray, Achievement[].class);

        for (Achievement achievement : achievements) {
            System.out.println(achievement);
        }

        return achievements;
    }

    public void createAchievement(String name, String description, String logo) throws IOException, InterruptedException {
        Achievement achievement = new Achievement();
        achievement.setName(name);
        achievement.setDescription(description);
        achievement.setLogo(logo);

        String mutation = "mutation MyMutation($name: String!, $description: String!, $logo: String!) { addAchievement(input: {name: $name, description: $description, logo: $logo}) { numUids }}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"name\":\"" + name + "\",\"description\":\"" + description + "\",\"logo\":\"" + logo + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void updateAchievement(String id, String name, String description, String logo) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!], $name: String!, $description: String!, $logo: String!) { updateAchievement(input: {filter: {id: $id}, set: {description: $description, logo: $logo, name: $name}}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"description\":\"" + description + "\",\"logo\":\"" + logo + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);

        System.out.println(response.body());
    }

    public void deleteAchievements(String id) throws IOException, InterruptedException {
        String mutation = "mutation MyMutation($id: [ID!]) { deleteAchievement(filter: {id: $id}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\",\"variables\":{\"id\":\"" + id + "\"}}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }

    public void deleteAllAchievements() throws IOException, InterruptedException {
        String mutation = "mutation MyMutation { deleteAchievement(filter: {}) {numUids}}";

        String requestBody = "{\"query\":\"" + mutation + "\"}";

        HttpResponse<String> response = _httpService.makeHttpRequest(requestBody);
    }

}
