package com.adaptionsoft.games;

import java.util.ArrayList;

public class Game {
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	public static final int PLAYER_NUMBERS = 6;

	ArrayList<Player> playerMembers = new ArrayList<Player>();
	Questions questions = new Questions();

    int currentPlayer = 0;

    public Game(){
    	questions.init();
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {


		initPlayers(playerName);
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + playerMembers.size());
		return true;
	}

	private void initPlayers(String playerName) {
		playerMembers.add(new Player(playerName, questions));
	}

	public int howManyPlayers() {
		return playerMembers.size();
	}

	public void roll(int roll) {
		Player player = getCurrentPlayer();
		player.roll(roll);
	}

	private boolean isInPenaltyBox() {
    	Player player = getCurrentPlayer();
    	return player.isInPenaltyBox();
	}

	public boolean wasCorrectlyAnswered() {
		if (!isInPenaltyBox()) {
			return doSomeWhenCorrectlyanswered();
		}

		if (!isGettingOutOfPenaltyBox()) {
			currentPlayerAdd();
			return true;
		}

		return doSomeWhenCorrectlyanswered();
	}

	private boolean isGettingOutOfPenaltyBox() {
		Player player = getCurrentPlayer();
		return player.isGettingOutOfPenaltyBox();
	}

	private void currentPlayerAdd() {
		currentPlayer++;
		if (currentPlayer == playerMembers.size()) {
			currentPlayer = 0;
		}
	}

	private boolean doSome() {
		currentPlayerAdd();
		Player player = getCurrentPlayer();
		boolean winner = player.doSomeWhenCorrectlyAnswered();
		return winner;
	}

	public boolean doSomeTemp()
	{
		Player player = getCurrentPlayer();
		boolean winner = player.doSomeWhenCorrectlyAnswered();
		currentPlayerAdd();
		return winner;
	}

	private boolean doSomeWhenCorrectlyanswered() {
		Player player = getCurrentPlayer();
		boolean winner = player.doSomeWhenCorrectlyAnswered();
		currentPlayerAdd();
		return winner;
	}

	private int getCurrentPurses() {
		Player player = getCurrentPlayer();
		return player.getPurses();
	}

	public boolean wrongAnswer(){
		Player player = getCurrentPlayer();
		player.wrongAnswer();
		currentPlayerAdd();
		return true;
	}

	private Player getCurrentPlayer() {
		return playerMembers.get(currentPlayer);
	}
}
