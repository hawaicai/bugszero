package com.adaptionsoft.games;

import java.util.LinkedList;

public class Deck {
    private final String category;
    LinkedList<String> questions = new LinkedList();
    public Deck(String category) {
        this.category = category;
        for (int i = 0; i < 50; i++) {
            questions.addLast(category + " Question " + i);
        }
    }

    public String getNextQuestion() {
        return questions.removeFirst();
    }
}
