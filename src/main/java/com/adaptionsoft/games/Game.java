package com.adaptionsoft.games;

import java.util.ArrayList;

public class Game {

	ArrayList<Player> players = new ArrayList<Player>();
	private final DecksManager decksManager = new DecksManager();
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
				gettingOutOfpenaltyBox();
				movePlayerAndAskQuestion(roll);
			} else {
				stadyInPenaltyBox();
			}
		} else {
			movePlayerAndAskQuestion(roll);
		}

	}

	private void stadyInPenaltyBox() {
		System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
		isGettingOutOfPenaltyBox = false;
	}

	private void gettingOutOfpenaltyBox() {
		isGettingOutOfPenaltyBox = true;

		System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
	}

	private boolean isInPenaltyBox() {
		return getCurrentPlayer().isInpenaltyBox();
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	private Object getCurrentPlayerName() {
		return getCurrentPlayer().getName();
	}

	private void movePlayerAndAskQuestion(int roll) {
		movePlayer(roll);
		askQuestion();
	}

	private void movePlayer(int roll) {
		getCurrentPlayer().forWard(roll);
	}

	private void askQuestion() {
		String currentCategory = decksManager.currentCategory(getCurrentPlayerPlace());
		String questions = decksManager.askQuestion(currentCategory);
		System.out.println("The category is " + currentCategory);
		System.out.println(questions);
	}

	private int getCurrentPlayerPlace() {
		return getCurrentPlayer().getPalces();
	}

	public boolean wasCorrectlyAnswered() {
		if (isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				toNextPlayer();
				increasePlayerGoldCoins();

				boolean winner = didPlayerWin();

				return winner;
			} else {
				toNextPlayer();
				return true;
			}
		} else {
			System.out.println("Answer was correct!!!!");
			increasePlayerGoldCoins();

			boolean winner = didPlayerWin();
			toNextPlayer();

			return winner;
		}
	}

	private void increasePlayerGoldCoins() {
		getCurrentPlayer().increasePlayerGoldCoins();
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
		setToPenaltyBox();

		toNextPlayer();
		return true;
	}

	private void setToPenaltyBox() {
		getCurrentPlayer().setToPenaltyBox();
	}


	private boolean didPlayerWin() {
		return !(getCurrentCoins() == 6);
	}

	private int getCurrentCoins() {
		return getCurrentPlayer().getGoldCoins();
	}
}
