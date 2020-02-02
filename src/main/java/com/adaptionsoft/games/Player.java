package com.adaptionsoft.games;

import static com.adaptionsoft.games.Game.PLAYER_NUMBERS;

public class Player {
    private final String name;
    private final Questions questions;
    private int places;
    private int purses;
    private boolean inPenaltyBox;

    public Player(String playerName, Questions questions) {
        this.name = playerName;
        this.questions = questions;
        this.places = 0;
        this.purses = 0;
        inPenaltyBox = false;
    }

    public void addRoll(int roll) {
        this.places += roll;
        if (this.places > 11) {
            this.places -= 12;
        }
    }

    public void askQuestion() {
        Object obj = questions.getQuestionsByPlace(getPlace());
        System.out.println(obj);
    }

    public void movePlayerAndAskQuestion(int roll) {
        addRoll(roll);

        System.out.println(getPlayerName()
                + "'s new location is "
                + getPlace());
        System.out.println("The category is " + questions.currentCategory(getPlace()));

        askQuestion();
    }

    public String getPlayerName() {
        return this.name;
    }

    public int getPlace() {
        return this.places;
    }

    public void increasepurses() {
        this.purses++;
    }

    public int getPurses() {
        return this.purses;
    }

    public boolean didPlayerWin() {
        return !(getPurses() == PLAYER_NUMBERS);
    }

    public boolean wasCorrectlyAnswered() {
        boolean winner = true;
        if (!this.inPenaltyBox)
        {
            winner = doSomeWhenCorrectlyAnswered();
        }
        return winner;
    }

    public boolean doSomeWhenCorrectlyAnswered() {
        increasepurses();
        System.out.println("Answer was correct!!!!");
        System.out.println(getPlayerName()
                + " now has "
                + getPurses()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        return winner;
    }

    public boolean wrongAnswer(){
        System.out.println("Question was incorrectly answered");
        System.out.println(getPlayerName() + " was sent to the penalty box");
        this.inPenaltyBox = true;
        return true;
    }

    public void roll(int roll) {
        System.out.println(getPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        if (!isInpenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }
        if (rollIsDermainder(roll)) {
            System.out.println(getPlayerName() + " is not getting out of the penalty box");
        }
        else{
            System.out.println(getPlayerName() + " is getting out of the penalty box");

            movePlayerAndAskQuestion(roll);
        }
        return;
    }
    private boolean rollIsDermainder(int roll) {
        return roll % 2 == 0;
    }

    public void setToPenaltyBox() {
        this.inPenaltyBox = true;
    }

    public void setOutOfPenaltyBox() {
        this.inPenaltyBox = false;
    }

    public boolean isInpenaltyBox() {
        return this.inPenaltyBox;
    }
}
