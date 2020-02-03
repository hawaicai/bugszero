package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.Map;

public class QuestionsManager {
    private Map<String, Deck> decks = new HashMap<>();

    public QuestionsManager()
    {
        for (Category category:Category.values()) {
            decks.put(category.getName(), new Deck(category.getName()));
        }
    }

    public String askQuestion(int places) {
        String category = currentCategory(places);
        return decks.get(category).getNextQuestion();
        }

    public String currentCategory(int places) {
        return Category.currentCategory(places);
    }
}
