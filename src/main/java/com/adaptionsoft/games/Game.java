package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    ArrayList<Player> playerMembers = new ArrayList<Player>();
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    private final QuestionsManager questionsManager = new QuestionsManager();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {

	    players.add(playerName);
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
		playerMembers.add(new Player(playerName));

	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}

	public int howManyPlayers() {
		return playerMembers.size();
	}

	public void roll(int roll) {
		System.out.println(getCurrentPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
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

	private void stadyInPenaltyBox() {
		System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
		isGettingOutOfPenaltyBox = false;
	}

	private void gettingOutOfPenaltyBox() {
		isGettingOutOfPenaltyBox = true;

		System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
	}

	private Object getCurrentPlayerName() {
		return players.get(currentPlayer);
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
		playerMembers.get(currentPlayer).forwardPlaces(roll);
	}

	private void askQuestion() {
		System.out.println(questionsManager.askQuestion(getCurrentPlayerPlaces()));
	}

	private int getCurrentPlayerPlaces() {
		return playerMembers.get(currentPlayer).getPlaces();
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
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
		purses[currentPlayer]++;
		System.out.println(getCurrentPlayerName()
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		setPlayerToPenaltyBox();

		toNextPlayer();
		return true;
	}

	private void setPlayerToPenaltyBox() {
		System.out.println(getCurrentPlayerName() + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
