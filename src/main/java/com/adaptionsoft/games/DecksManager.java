package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DecksManager {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    private Map<String, Deck> decks = new HashMap<>();
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
        Category[] categories = Category.values();
        int size = categories.length;
        return categories[place % size].getName();
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
