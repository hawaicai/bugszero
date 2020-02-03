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
		movePlayer(roll);

		askQuestion();
	}

	private void movePlayer(int roll) {
		forwardPlaces(roll);

		System.out.println(getCurrentPlayerName()
                + "'s new location is "
                + getCurrentPlayerPlaces());
		System.out.println("The category is " + questionsManager.currentCategory(getCurrentPlayerPlaces()));
	}

	private void forwardPlaces(int roll) {
		getCurrentPlayer().forwardPlaces(roll);
	}

	private void askQuestion() {
		System.out.println(questionsManager.askQuestion(getCurrentPlayerPlaces()));
	}

	private int getCurrentPlayerPlaces() {
		return getCurrentPlayer().getPlaces();
	}

	public boolean wasCorrectlyAnswered() {
		if (isInPenaltyBox()){
			if (isGettingOutOfPenaltyBox) {
				toNextPlayer();
				answerCorrectAndIncreaseCoins();

				boolean winner = didPlayerWin();

				return winner;
			} else {
				toNextPlayer();
				return true;
			}



		} else {

			answerCorrectAndIncreaseCoins();

			boolean winner = didPlayerWin();
			toNextPlayer();

			return winner;
		}
	}

	private void answerCorrectAndIncreaseCoins() {
		System.out.println("Answer was correct!!!!");
		increaseCoins();
		System.out.println(getCurrentPlayerName()
				+ " now has "
				+ getCurrentCoins()
				+ " Gold Coins.");
	}

	private int getCurrentCoins() {
		return getCurrentPlayer().getGoldCoins();
	}

	private void increaseCoins() {
		getCurrentPlayer().increaseCoins();
	}

	private Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		setPlayerToPenaltyBox();

		toNextPlayer();
		return true;
	}

	private void setPlayerToPenaltyBox() {
		getCurrentPlayer().setToPenaltyBox();
	}

	private boolean didPlayerWin() {
		return !(getCurrentCoins() == 6);
	}
}
