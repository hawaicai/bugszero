package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QuestionsManager {
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    private Map<String, Deck> decks = new HashMap<>();
    public QuestionsManager()
    {
        decks.put(POP, new Deck(POP));
        decks.put(SCIENCE, new Deck(SCIENCE));
        decks.put(SPORTS,new Deck(SPORTS));
        decks.put(ROCK, new Deck(ROCK));
    }

    public String askQuestion(int places) {
        String category = currentCategory(places);
        return decks.get(category).getNextQuestion();
        }

    public String currentCategory(int places) {
        if (places == 0) return POP;
        if (places == 4) return POP;
        if (places == 8) return POP;
        if (places == 1) return SCIENCE;
        if (places == 5) return SCIENCE;
        if (places == 9) return SCIENCE;
        if (places == 2) return SPORTS;
        if (places == 6) return SPORTS;
        if (places == 10) return SPORTS;
        return ROCK;
    }
}
