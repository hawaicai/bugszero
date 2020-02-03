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

    public String askQuestion(String category) {
        if (category == "Pop")
            return (String) popQuestions.removeFirst();
        if (category == "Science")
            return (String) scienceQuestions.removeFirst();
        if (category == "Sports")
            return (String) sportsQuestions.removeFirst();
        if (category == "Rock")
            return (String) rockQuestions.removeFirst();
        return "";
    }
}
