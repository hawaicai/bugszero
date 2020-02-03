package com.adaptionsoft.games;

import java.util.Random;

public class Game {
	Players players = new Players();
    private final QuestionsManager questionsManager = new QuestionsManager();

	public boolean add(String playerName) {
		Player player = new Player(playerName);
		players.add(player);
	    System.out.println("They are player number " + players.howManyPlayers());
		return true;
	}

	public void start(Random rand)
	{
		boolean isAWinner = false;
		do {
			boolean isAAnswer = roll(rand.nextInt(5) + 1);
			if (isAAnswer) {
				answerQuestion(rand);
			}
			isAWinner = didCurrentPlayerIsAWinner();
			toNextPlayer();
		} while (!isAWinner);

	}

	private void answerQuestion(Random rand) {
		askQuestion();
		boolean wrongResult = rand.nextInt(9) == 7;
		answerAndIsWinner(wrongResult);
	}

	private void answerAndIsWinner(boolean result) {
		if (result) {
			wasWrongAnswer();
		} else {
			wasCorrectlyAnswered();
		}
	}

	public boolean roll(int roll) {
		return getCurrentPlayer().playerRoll(roll);
	}

	void askQuestion() {
		final int places = getCurrentPlayer().getPlaces();
		System.out.println("The category is " + questionsManager.currentCategory(places));
		System.out.println(questionsManager.askQuestion(places));
	}

	private void wasCorrectlyAnswered() {
		System.out.println("Answer was correct!!!!");
		getCurrentPlayer().increaseCoins();
	}
	public void wasWrongAnswer(){
		System.out.println("Question was incorrectly answered");
		getCurrentPlayer().setToPenaltyBox();
	}

	private boolean didCurrentPlayerIsAWinner() {
		return getCurrentPlayer().isAWinner();
	}

	private Player getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	void toNextPlayer() {
		players.toNextPlayer();
	}
}
