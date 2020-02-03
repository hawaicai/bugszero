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
		boolean asAWinner = false;
		do {
			boolean isAnswer = roll(rand.nextInt(5) + 1);
			if (isAnswer) {
				askQuestion();
				boolean wrongResult = rand.nextInt(9) == 7;
				asAWinner = answerAndIsWinner(wrongResult);
			}
			toNextPlayer();
		} while (!asAWinner);

	}

	private boolean answerAndIsWinner(boolean result) {
		if (result) {
			return wasWrongAnswer();
		} else {
			return wasCorrectlyAnswered();
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

	public boolean wasCorrectlyAnswered() {
		System.out.println("Answer was correct!!!!");
		boolean isWinner = getCurrentPlayer().increaseCoinsAndReturnIsWinner();
		return isWinner;
	}

	private Player getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	void toNextPlayer() {
		players.toNextPlayer();
	}

	public boolean wasWrongAnswer(){
		System.out.println("Question was incorrectly answered");
		getCurrentPlayer().setToPenaltyBox();
		return false;
	}
}
