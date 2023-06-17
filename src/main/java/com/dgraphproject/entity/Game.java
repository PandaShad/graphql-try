package com.dgraphproject.entity;

import com.google.type.Date;

public class Game {
    private String id;
    private Date date;
    private User opponent;
    private int eloVariation;
    private String result;
    private Deck usedDeck;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public int getEloVariation() {
        return eloVariation;
    }

    public void setEloVariation(int eloVariation) {
        this.eloVariation = eloVariation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Deck getUsedDeck() {
        return usedDeck;
    }

    public void setUsedDeck(Deck usedDeck) {
        this.usedDeck = usedDeck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date=" + date +
                ", opponent=" + opponent +
                ", eloVariation=" + eloVariation +
                ", result='" + result + '\'' +
                ", usedDeck=" + usedDeck +
                ", type='" + type + '\'' +
                '}';
    }
}
