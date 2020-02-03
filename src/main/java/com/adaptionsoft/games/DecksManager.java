package com.adaptionsoft.games;

import java.util.LinkedList;

public class DecksManager {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    public DecksManager()
    {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    protected String currentCategory(int place) {
        int[] places = {place};
        int currentPlayer = 0;
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    protected void askQuestion(int place) {
        String category = currentCategory(place);
        if (category == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (category == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (category == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (category == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }

}
