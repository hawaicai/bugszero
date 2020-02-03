package com.adaptionsoft.games;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    private final QuestionsManager questionsManager = new QuestionsManager();
    int currentPlayer = 0;

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		players.add(new Player(playerName));
	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public boolean roll(int roll) {
		return getCurrentPlayer().playerRoll(roll);
	}

	void askQuestion() {
		final int places = getCurrentPlayer().getPlaces();
		System.out.println("The category is " + questionsManager.currentCategory(places));
		System.out.println(questionsManager.askQuestion(places));
	}

	public boolean wasCorrectlyAnswered() {
		if (getCurrentPlayer().isInPenaltyBox()){
				return true;
		} else {
			return increaseCoinsAndNextPlayer();
		}
	}

	private boolean increaseCoinsAndNextPlayer() {
		getCurrentPlayer().increaseCoins();
		boolean winner = didPlayerWin();
		return winner;
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()){
			currentPlayer = 0;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		getCurrentPlayer().setToPenaltyBox();
		return true;
	}

	private boolean didPlayerWin() {
		return !(getCurrentPlayer().getGoldCoins() == 6);
	}
}
