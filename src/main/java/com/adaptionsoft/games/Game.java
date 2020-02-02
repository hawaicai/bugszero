package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	public static final int PLAYER_NUMBERS = 6;

	ArrayList<Player> playerMembers = new ArrayList<Player>();
	Questions questions = new Questions();

    int currentPlayer = 0;

    public Game(){
    	questions.init();
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		playerMembers.add(new Player(playerName, questions));
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}

	public int howManyPlayers() {
		return playerMembers.size();
	}

	public void roll(int roll) {
		Player player = getCurrentPlayer();
		player.roll(roll);
	}

	public boolean wasCorrectlyAnswered() {
    	Player player = getCurrentPlayer();
    	boolean winner = player.wasCorrectlyAnswered();
    	toNextPlayer();
		return winner;
	}

	public boolean wrongAnswer(){
		Player player = getCurrentPlayer();
		player.wrongAnswer();
		toNextPlayer();
		return true;
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == playerMembers.size()) {
			currentPlayer = 0;
		}
	}

	private Player getCurrentPlayer() {
		return playerMembers.get(currentPlayer);
	}
}
