package com.adaptionsoft.games;

public class Player {
    private final String name;
    private int places = 0;

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
    }
}
