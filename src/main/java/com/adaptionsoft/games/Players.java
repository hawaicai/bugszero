package com.adaptionsoft.games;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();
    int currentPlayer = 0;

    public int howManyPlayers() {
        return players.size();
    }

    void toNextPlayer() {
        currentPlayer++;
        if (currentPlayer == howManyPlayers()) currentPlayer = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void add(Player playerName) {
        players.add(playerName);
    }
}
