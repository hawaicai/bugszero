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
		boolean notAWinner = true;
		do {
			roll(rand.nextInt(5) + 1);
			final boolean isWrongAnswer = rand.nextInt(9) == 7;

			if (!players.getCurrentPlayer().isInPenaltyBox()) {
				if (isWrongAnswer) {
					wasWrongAnswer();
				} else {
					wasCorrectlyAnswered();
				}
				notAWinner = !didCurrentPlayerIsAWinner();
			}
			players.toNextPlayer();
		} while (notAWinner);
	}

	private void roll(int roll) {
		System.out.println(players.getCurrentPlayer().getName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (!players.getCurrentPlayer().isInPenaltyBox()) {
			movePlayerAndAskQuestion(roll);
			return;
		}

		if (isOdd(roll)) {
			players.getCurrentPlayer().gettingOutOfPenaltyBox();
			movePlayerAndAskQuestion(roll);
			return;
		}

		players.getCurrentPlayer().stadyInPenaltyBox();
	}

	private boolean isOdd(int number) {
		return number % 2 != 0;
	}

	private void movePlayerAndAskQuestion(int roll) {
		players.getCurrentPlayer().forwardPlaces(roll);
		askQuestion();
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

}
