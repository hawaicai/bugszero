package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int places;
    private int purses;
    private boolean inPenaltyBox;
    private boolean isGettingOutOfPenaltyBox;

    public Player(String playerName) {
        this.name = playerName;
        this.places = 0;
        this.purses = 0;
        this.inPenaltyBox = false;
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

    public void Increasepurses() {
        this.purses++;
    }

    public int getPurses() {
        return this.purses;
    }

    public void setToPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public boolean isInPenaltyBox() {
        return this.inPenaltyBox;
    }

    public void setGettingOutOfPenaltyBox(boolean b) {
        this.isGettingOutOfPenaltyBox = b;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return this.isGettingOutOfPenaltyBox;
    }
}
