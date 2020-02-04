package com.adaptionsoft.games;

public class Player {
    private final String name;
    private boolean inPenaltyBox = false;
    private int goldCoins = 0;
    private int places = 0;

    public Player(String playerName) {
        this.name = playerName;
        System.out.println(playerName + " was added");
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

    public void forWard(int roll) {
        places += roll;
        if (places > 11) {
            places -= 12;
        }
        System.out.println(getName()
                + "'s new location is "
                + places);

    }

    public int getPalces() {
        return places;
    }

    public void getOutOfPenaltyBox() {
        inPenaltyBox = false;
    }

    boolean didPlayerWin() {
        return goldCoins == 6;
    }
}
