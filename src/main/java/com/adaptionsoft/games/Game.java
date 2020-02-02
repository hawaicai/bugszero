package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	public static final int PLAYER_NUMBERS = 6;

	ArrayList<Player> playerMembers = new ArrayList<Player>();
	Map<String, Player> penaltyBox = new HashMap<String, Player>();
	Questions questions = new Questions();

    int currentPlayer = 0;

    public Game(){
    	questions.init();
    }

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		playerMembers.add(new Player(playerName, questions));
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + howManyPlayers());
		return true;
	}

	public int howManyPlayers() {
		return playerMembers.size();
	}

	public void roll(int roll) {
		Player player = getCurrentPlayer();
		System.out.println(player.getPlayerName() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (isInPenaltyBox(player)) {
			player.movePlayerAndAskQuestion(roll);
			return;
		}
		if (rollIsDermainder(roll)) {
			System.out.println(player.getPlayerName() + " is not getting out of the penalty box");
		}
		else{
			System.out.println(player.getPlayerName() + " is getting out of the penalty box");
			removeFromPenaltyBox(player);
			player.movePlayerAndAskQuestion(roll);
		}
		return;
	}

	private boolean isInPenaltyBox(Player player) {
		return penaltyBox.get(player.getPlayerName()) == null;
	}

	private void removeFromPenaltyBox(Player player) {
		penaltyBox.remove(player.getPlayerName());
	}

	private boolean rollIsDermainder(int roll) {
		return roll % 2 == 0;
	}

	public boolean wasCorrectlyAnswered() {
    	Player player = getCurrentPlayer();
    	boolean winner = true;
    	if (isInPenaltyBox(player))
		{
			winner = player.doSomeWhenCorrectlyAnswered();
		}
		toNextPlayer();
		return winner;
	}

	public boolean wrongAnswer(){
		Player player = getCurrentPlayer();
		player.wrongAnswer();
		setToPenaltyBox(player);
		toNextPlayer();
		return true;
	}

	private void setToPenaltyBox(Player player) {
		penaltyBox.put(player.getPlayerName(), player);
	}

	private void toNextPlayer() {
		currentPlayer++;
		if (currentPlayer == playerMembers.size()) {
			currentPlayer = 0;
		}
	}

	private Player getCurrentPlayer() {
		return playerMembers.get(currentPlayer);
	}
}
