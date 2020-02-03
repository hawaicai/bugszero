package com.adaptionsoft.games;

import java.util.LinkedList;

public class QuestionsManager {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    public QuestionsManager()
    {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void askQuestion(int places) {
        String category = currentCategory(places);
        if (category == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (category == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (category == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (category == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }

    public String currentCategory(int places) {
        if (places == 0) return "Pop";
        if (places == 4) return "Pop";
        if (places == 8) return "Pop";
        if (places == 1) return "Science";
        if (places == 5) return "Science";
        if (places == 9) return "Science";
        if (places == 2) return "Sports";
        if (places == 6) return "Sports";
        if (places == 10) return "Sports";
        return "Rock";
    }
}
