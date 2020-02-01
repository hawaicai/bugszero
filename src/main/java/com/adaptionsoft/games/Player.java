package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int places;

    public Player(String playerName) {
        this.name = playerName;
        this.places = 0;
    }

    public void addRoll(int roll) {
        this.places += roll;
        if (this.places > 11) {
            this.places -= 12;
        }
    }

    public String getPlayerName() {
        return this.name;
    }

    public int getPlace() {
        return this.places;
    }
}
