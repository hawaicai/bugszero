package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	public static final String POP = "Pop";
	public static final String SCIENCE = "Science";
	public static final String SPORTS = "Sports";
	public static final String ROCK = "Rock";
	ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

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
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (!isInPenaltyBox()) {
			movePlayerAndAskQuestion(roll);
			return;
		}

		if (rollIsDermainder(roll)) {
			System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
			setGettingOutOfpanaltyBox(false);
			return;
		}

		setGettingOutOfpanaltyBox(true);
		System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
		movePlayerAndAskQuestion(roll);
		return;
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
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) {
			places[currentPlayer] = places[currentPlayer] - 12;
		}

		System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
		askQuestion();
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
		if (places[currentPlayer] == 2) return true;
		if (places[currentPlayer] == 6) return true;
		if (places[currentPlayer] == 10) return true;
		return false;
	}

	private boolean currentPlayerIsSCIENCE() {
		if (places[currentPlayer] == 1) return true;
		if (places[currentPlayer] == 5) return true;
		if (places[currentPlayer] == 9) return true;
		return false;
	}

	private boolean currentPlayerIsPOP() {
		if (places[currentPlayer] == 0) return true;
		if (places[currentPlayer] == 4) return true;
		if (places[currentPlayer] == 8) return true;
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
		purses[currentPlayer]++;
		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");

		boolean winner = didPlayerWin();

		return winner;
	}

	private boolean doSomeWhenCorrectlyanswered() {
		System.out.println("Answer was corrent!!!!");
		purses[currentPlayer]++;
		System.out.println(players.get(currentPlayer)
				+ " now has "
				+ purses[currentPlayer]
				+ " Gold Coins.");

		boolean winner = didPlayerWin();
		currentPlayerAdd();

		return winner;
	}

	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		setToPenaltyBox();

		currentPlayerAdd();
		return true;
	}

	private void setToPenaltyBox() {
		inPenaltyBox[currentPlayer] = true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
