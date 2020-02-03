package com.adaptionsoft.games;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();

    private final QuestionsManager questionsManager = new QuestionsManager();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

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
		isGettingOutOfPenaltyBox = false;
	}

	private void gettingOutOfPenaltyBox() {
		isGettingOutOfPenaltyBox = true;
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
			if (isGettingOutOfPenaltyBox) {
				toNextPlayer();
				getCurrentPlayer().increaseCoins();
				boolean winner = didPlayerWin();
				return winner;
			}
			else {
				toNextPlayer();
				return true;
			}
		} else {
			getCurrentPlayer().increaseCoins();
			boolean winner = didPlayerWin();
			toNextPlayer();
			return winner;
		}
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()){
			currentPlayer = 0;
		}
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		getCurrentPlayer().setToPenaltyBox();

		toNextPlayer();
		return true;
	}

	private boolean didPlayerWin() {
		return !(getCurrentPlayer().getGoldCoins() == 6);
	}
}
