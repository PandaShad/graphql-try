package com.dgraphproject.entity;

import java.util.Arrays;

public class Deck {

    private String id;
    private String name;
    private int maxCardNumber;
    private Card[] cards;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCardNumber() {
        return this.maxCardNumber;
    }

    public void setMaxCardNumber(int maxCardNumber) {
        this.maxCardNumber = maxCardNumber;
    }

    public Card[] getCard() {
        return this.cards;
    }

    public void setCard(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxCardNumber=" + maxCardNumber +
                ", cards=" + Arrays.toString(cards) +
                '}';
    }
}