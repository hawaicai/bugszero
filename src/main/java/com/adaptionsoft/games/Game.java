package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	Players playTmp = new Players();
	ArrayList<Player> players = new ArrayList<Player>();
	private final DecksManager decksManager = new DecksManager();
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

	public void start(Random rand)
	{
		if (!isPlayable())
		{
			System.out.println("There is not enough players!");
			return;
		}
		boolean notAWinner;
		do {
			roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				wasWrongAnswer();
			} else {
				wasCorrectlyAnswered();
			}
			notAWinner = didPlayerNotWin();
			toNextPlayer();
		} while (notAWinner);
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

	void askQuestion() {
		String currentCategory = decksManager.currentCategory(getCurrentPlayerPlace());
		String questions = decksManager.askQuestion(currentCategory);
		System.out.println("The category is " + currentCategory);
		System.out.println(questions);
	}

	private int getCurrentPlayerPlace() {
		return getCurrentPlayer().getPalces();
	}

	public void wasCorrectlyAnswered() {
		if (isInPenaltyBox()){
				return;
		}
		else {
			System.out.println("Answer was correct!!!!");
			increasePlayerGoldCoins();
		}
	}

	private void increasePlayerGoldCoins() {
		getCurrentPlayer().increasePlayerGoldCoins();
	}

	void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == howManyPlayers()) currentPlayer = 0;
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
		return !(getCurrentCoins() == 6);
	}

	private int getCurrentCoins() {
		return getCurrentPlayer().getGoldCoins();
	}
}
