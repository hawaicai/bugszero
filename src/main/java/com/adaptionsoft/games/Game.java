package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	public static final int PLAYER_NUMBERS = 6;
	ArrayList players = new ArrayList();
    int[] places = new int[PLAYER_NUMBERS];
    Player[] playerMembers = new Player[PLAYER_NUMBERS];
    int[] purses  = new int[PLAYER_NUMBERS];
    boolean[] inPenaltyBox  = new boolean[PLAYER_NUMBERS];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {


	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
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
		return players.get(currentPlayer);
	}

	private void setGettingOutOfpanaltyBox(boolean b) {
		isGettingOutOfPenaltyBox = b;
	}

	private boolean rollIsDermainder(int roll) {
		return roll % 2 == 0;
	}

	private boolean isInPenaltyBox() {
		return inPenaltyBox[currentPlayer];
	}

	private void movePlayerAndAskQuestion(int roll) {
		CurrentPlaceAdd(roll);

		System.out.println(getCurrentplayer()
                + "'s new location is "
                + getPlaceCurrent());
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void CurrentPlaceAdd(int roll) {
		places[currentPlayer] = getPlaceCurrent() + roll;
		if (getPlaceCurrent() > 11) {
			places[currentPlayer] = getPlaceCurrent() - 12;
		}
	}

	private int getPlaceCurrent() {
		return places[currentPlayer];
	}

	private void askQuestion() {
		if (currentCategory() == POP)
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == SCIENCE)
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == SPORTS)
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == ROCK)
			System.out.println(rockQuestions.removeFirst());
	}


	private String currentCategory() {
		if (currentPlayerIsPOP()) return POP;
		if (currentPlayerIsSCIENCE()) return SCIENCE;
		if (currentPlayerIsSPORTS()) return SPORTS;
		return ROCK;
	}

	private boolean currentPlayerIsSPORTS() {
		if (getPlaceCurrent() == 2) return true;
		if (getPlaceCurrent() == PLAYER_NUMBERS) return true;
		if (getPlaceCurrent() == 10) return true;
		return false;
	}

	private boolean currentPlayerIsSCIENCE() {
		if (getPlaceCurrent() == 1) return true;
		if (getPlaceCurrent() == 5) return true;
		if (getPlaceCurrent() == 9) return true;
		return false;
	}

	private boolean currentPlayerIsPOP() {
		if (getPlaceCurrent() == 0) return true;
		if (getPlaceCurrent() == 4) return true;
		if (getPlaceCurrent() == 8) return true;
		return false;
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
		return isGettingOutOfPenaltyBox;
	}

	private void currentPlayerAdd() {
		currentPlayer++;
		if (currentPlayer == players.size()) {
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
		purses[currentPlayer]++;
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
		return purses[currentPlayer];
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getCurrentplayer() + " was sent to the penalty box");
		setToPenaltyBox();

		currentPlayerAdd();
		return true;
	}

	private void setToPenaltyBox() {
		inPenaltyBox[currentPlayer] = true;
	}


	private boolean didPlayerWin() {
		return !(getCurrentPurses() == PLAYER_NUMBERS);
	}
}
