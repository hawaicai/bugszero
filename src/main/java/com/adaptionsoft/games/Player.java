package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int places = 0;
    private int coins = 0;
    private boolean inPenaltyBox = false;

    public Player(String playerName) {
        name = playerName;
        System.out.println(playerName + " was added");
    }

    public int getPlaces() {
        return places;
    }

    public void forwardPlaces(int roll) {
        places +=  roll;
        if (places > 11) {
            places -= 12;
        }
        System.out.println(getName()
                + "'s new location is "
                + getPlaces());

    }

    public void increaseCoins() {
        System.out.println("Answer was correct!!!!");
        coins++;
        System.out.println(getName()
                + " now has "
                + getGoldCoins()
                + " Gold Coins.");
    }

    public int getGoldCoins() {
        return coins;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setToPenaltyBox() {
        System.out.println(getName() + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    String getName() {
        return name;
    }

    public void getOutOfPenaltyBox() {

    }
}
