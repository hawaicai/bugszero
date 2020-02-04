package com.adaptionsoft.games;

import java.util.Random;

public class Game {

	Players players = new Players();
	private final DecksManager decksManager = new DecksManager();

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		players.add(playerName);
	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}

	public int howManyPlayers() {
		return players.howManyPlayers();
	}

	public void start(Random rand)
	{
		if (!isPlayable())
		{
			System.out.println("There is not enough players!");
			return;
		}
		boolean notAWinner = true;
		do {
			roll(rand.nextInt(5) + 1);
			if (!isInPenaltyBox()) {
				answer(rand);
				notAWinner = didPlayerNotWin();
			}
			toNextPlayer();
		} while (notAWinner);
	}

	private void answer(Random rand) {
		if (rand.nextInt(9) == 7) {
			wasWrongAnswer();
		} else {
			wasCorrectlyAnswered();
		}
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
	}

	private void gettingOutOfpenaltyBox() {
		getCurrentPlayer().getOutOfPenaltyBox();
		System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
	}

	private boolean isInPenaltyBox() {
		return getCurrentPlayer().isInpenaltyBox();
	}

	Player getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	private String getCurrentPlayerName() {
		return getCurrentPlayer().getName();
	}

	private void movePlayerAndAskQuestion(int roll) {
		movePlayer(roll);
		askQuestion();
	}

	private void movePlayer(int roll) {
		getCurrentPlayer().forWard(roll);
	}

	void askQuestion() {
		int place = getCurrentPlayer().getPalces();
		String currentCategory = decksManager.currentCategory(place);
		String questions = decksManager.askQuestion(currentCategory);
		System.out.println("The category is " + currentCategory);
		System.out.println(questions);
	}

	public void wasCorrectlyAnswered() {
		System.out.println("Answer was correct!!!!");
		increasePlayerGoldCoins();
	}

	private void increasePlayerGoldCoins() {
		getCurrentPlayer().increasePlayerGoldCoins();
	}

	void toNextPlayer() {
		players.toNextPlayer();
	}

	public void wasWrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
		setToPenaltyBox();
	}

	private void setToPenaltyBox() {
		getCurrentPlayer().setToPenaltyBox();
	}

	boolean didPlayerNotWin() {
		return !getCurrentPlayer().didPlayerWin();
	}

}
