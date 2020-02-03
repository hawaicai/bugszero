package com.adaptionsoft.games;

public class Player {
    private final String name;
    private boolean inPenaltyBox = false;

    public Player(String playerName) {
        this.name = playerName;
    }

    public String getName() {
        return name;
    }

    public void setToPenaltyBox() {
        inPenaltyBox = true;
    }

    public boolean isInpenaltyBox() {
        return inPenaltyBox;
    }
}
