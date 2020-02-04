package com.adaptionsoft.games;

import java.util.LinkedList;

public class Deck {
    private final String name;
    LinkedList<String> questions = new LinkedList<>();
    private int index = 0;

    public Deck(String name) {
        this.name = name;
        for (int i = 0; i < 50; i++) {
            questions.addLast(name + " Question " + i);
        }
    }

    public String getNextQuestion() {
        if (index == 50)
        {
            index = 0;
        }
        return questions.get(index++);
    }
}
