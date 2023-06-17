package com.dgraphproject.entity;

import java.util.Arrays;

public class User
{
    private String id;
    private String password;
    private int elo;
    private int level;
    private Card[] cardCollection;
    private Deck[] deckCollection;
    private Game[] gameHistory;
    private Achievement[] successedAchievement;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getElo() {
        return this.elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Card[] getCardCollection() {
        return this.cardCollection;
    }

    public void setCardCollection(Card[] cardCollection) {
        this.cardCollection = cardCollection;
    }

    public Deck[] getDeckCollection() {
        return this.deckCollection;
    }

    public void setDeckCollection(Deck[] deckCollection) {
        this.deckCollection = deckCollection;
    }

    public Game[] getGameHistory() {
        return this.gameHistory;
    }

    public void setGameHistory(Game[] gameHistory) {
        this.gameHistory = gameHistory;
    }

    public Achievement[] getSuccessedAchievement() {
        return this.successedAchievement;
    }

    public void setSuccessedAchievement(Achievement[] successedAchievement) {
        this.successedAchievement = successedAchievement;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", elo=" + elo +
                ", level=" + level +
                ", cardCollection=" + Arrays.toString(cardCollection) +
                ", deckCollection=" + Arrays.toString(deckCollection) +
                ", gameHistory=" + Arrays.toString(gameHistory) +
                ", successedAchievement=" + Arrays.toString(successedAchievement) +
                '}';
    }
}
