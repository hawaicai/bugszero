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

	public void roll(int roll) {
		System.out.println(getCurrentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (isInPenaltyBox()) {
			if (roll % 2 != 0) {
				gettingOutOfPenaltyBox();
				movePlayerAndAskQuestion(roll);
			} else {
				stadyInPenaltyBox();
			}

		} else {
			movePlayerAndAskQuestion(roll);
		}
	}

	private boolean isInPenaltyBox() {
		return getCurrentPlayer().isInPenaltyBox();
	}
	private void stadyInPenaltyBox() {
		System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
	}

	private void gettingOutOfPenaltyBox() {
		getCurrentPlayer().getOutOfPenaltyBox();
		System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
	}

	private Object getCurrentPlayerName() {
		return getCurrentPlayer().getName();
	}

	private void movePlayerAndAskQuestion(int roll) {
		getCurrentPlayer().forwardPlaces(roll);
		askQuestion();
	}

	private void askQuestion() {
		final int places = getCurrentPlayer().getPlaces();
		System.out.println("The category is " + questionsManager.currentCategory(places));
		System.out.println(questionsManager.askQuestion(places));
	}

	public boolean wasCorrectlyAnswered() {
		if (isInPenaltyBox()){
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
