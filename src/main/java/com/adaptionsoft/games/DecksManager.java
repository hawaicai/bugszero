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
        for (Category category:Category.values()) {
            decks.put(category.getName(), new Deck(category.getName()));
        }
    }

    protected String currentCategory(int place) {
        Category[] categories = Category.values();
        int size = categories.length;
        return categories[place % size].getName();
    }

    public String askQuestion(String category) {
        Deck deck = decks.get(category);
        if (null == deck)
        {
            return null;
        }
        return deck.getNextQuestion();
    }
}
