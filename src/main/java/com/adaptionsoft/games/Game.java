package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

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

	public String createRockQuestion(int index){
		return "Rock Question " + index;
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
		playerMembers.add(new Player(playerName));
	}

	public int howManyPlayers() {
		return playerMembers.size();
	}

	public void roll(int roll) {
		System.out.println(getCurrentplayer() + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (!isInPenaltyBox()) {
			movePlayerAndAskQuestion(roll);
			return;
		}

		if (rollIsDermainder(roll)) {
			System.out.println(getCurrentplayer() + " is not getting out of the penalty box");
			setGettingOutOfpanaltyBox(false);
			return;
		}

		setGettingOutOfpanaltyBox(true);
		System.out.println(getCurrentplayer() + " is getting out of the penalty box");
		movePlayerAndAskQuestion(roll);
		return;
	}

	private Object getCurrentplayer() {
		return playerMembers.get(currentPlayer).getPlayerName();
	}

	private void setGettingOutOfpanaltyBox(boolean b) {
		Player player = getCurrentPlayer();
		player.setGettingOutOfPenaltyBox(b);
	}

	private boolean rollIsDermainder(int roll) {
		return roll % 2 == 0;
	}

	private boolean isInPenaltyBox() {
    	Player player = getCurrentPlayer();
    	return player.isInPenaltyBox();
	}

	private void movePlayerAndAskQuestion(int roll) {
		currentPlaceAdd(roll);

		System.out.println(getCurrentplayer()
                + "'s new location is "
                + getPlaceCurrent());
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void currentPlaceAdd(int roll) {
		Player player = getCurrentPlayer();
		player.addRoll(roll);
	}

	private int getPlaceCurrent() {
		Player player = getCurrentPlayer();
		return player.getPlace();
	}

	private void askQuestion() {
		Object obj = getQuestionsByPlace();
		System.out.println(obj);
	}

	private Object getQuestionsByPlace() {
		Player player = getCurrentPlayer();
		return questions.getQuestionsByPlace(player.getPlace());
	}


	private String currentCategory() {
		if (currentPlayerIsPOP()) return POP;
		if (currentPlayerIsSCIENCE()) return SCIENCE;
		if (currentPlayerIsSPORTS()) return SPORTS;
		return ROCK;
	}

	private boolean currentPlayerIsSPORTS() {
		if (getPlaceCurrent() == 2) return true;
		if (getPlaceCurrent() == 6) return true;
		return getPlaceCurrent() == 10;
	}

	private boolean currentPlayerIsSCIENCE() {
		if (getPlaceCurrent() == 1) return true;
		if (getPlaceCurrent() == 5) return true;
		return getPlaceCurrent() == 9;
	}

	private boolean currentPlayerIsPOP() {
		if (getPlaceCurrent() == 0) return true;
		if (getPlaceCurrent() == 4) return true;
		return getPlaceCurrent() == 8;
	}

	public boolean wasCorrectlyAnswered() {
		if (!isInPenaltyBox()) {
			return doSomeWhenCorrectlyanswered();
		}

		if (!isGettingOutOfPenaltyBox()) {
			currentPlayerAdd();
			return true;
		}

		return doSome();
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
		System.out.println("Answer was correct!!!!");
		currentPlayerAdd();
		pursesAdd();
		System.out.println(getCurrentplayer()
				+ " now has "
				+ getCurrentPurses()
				+ " Gold Coins.");

		boolean winner = didPlayerWin();

		return winner;
	}

	private void pursesAdd() {
		Player player = getCurrentPlayer();
		player.Increasepurses();
	}
	private boolean doSomeWhenCorrectlyanswered() {
		System.out.println("Answer was corrent!!!!");
		pursesAdd();
		System.out.println(getCurrentplayer()
				+ " now has "
				+ getCurrentPurses()
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayerAdd();

		return winner;
	}

	private int getCurrentPurses() {
		Player player = getCurrentPlayer();
		return player.getPurses();
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentplayer() + " was sent to the penalty box");
		setToPenaltyBox();

		currentPlayerAdd();
		return true;
	}

	private void setToPenaltyBox() {
		Player player = getCurrentPlayer();
		player.setToPenaltyBox();
	}

	private Player getCurrentPlayer() {
		return playerMembers.get(currentPlayer);
	}


	private boolean didPlayerWin() {
		return !(getCurrentPurses() == PLAYER_NUMBERS);
	}
}
