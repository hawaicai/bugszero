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
        inPenaltyBox = false;
    }

    public void gettingOutOfPenaltyBox() {
        getOutOfPenaltyBox();
        System.out.println(getName() + " is getting out of the penalty box");
    }

    public void stadyInPenaltyBox() {
        System.out.println(getName() + " is not getting out of the penalty box");
    }

    public boolean playerRoll(int roll) {
        System.out.println(getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (isInPenaltyBox()) {
            if (roll % 2 != 0) {
                gettingOutOfPenaltyBox();
                forwardPlaces(roll);
                return true;
            } else {
                stadyInPenaltyBox();
                return false;
            }

        } else {
            forwardPlaces(roll);
            return true;
        }
    }
}
