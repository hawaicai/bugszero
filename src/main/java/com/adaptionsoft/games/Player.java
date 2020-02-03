package com.adaptionsoft.games;

public class Player {
    private final String name;
    private boolean inPenaltyBox = false;
    private int goldCoins;

    public Player(String playerName) {
        this.name = playerName;
        goldCoins = 0;
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

    public void increasePlayerGoldCoins() {
        goldCoins++;
        System.out.println(getName()
                + " now has "
                + goldCoins
                + " Gold Coins.");
    }

    public int getGoldCoins() {
        return goldCoins;
    }
}
