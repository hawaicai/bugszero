package com.adaptionsoft.games;

import java.util.LinkedList;

import static com.adaptionsoft.games.Game.POP;

public class QuestionCategory {
    private final String category;
    private LinkedList questions = new LinkedList();
    private int index;

    public QuestionCategory(String category) {
        this.category = category;
        index = 0;
        for (int i = 0; i < 50; i++) {
            questions.addLast(category + " Question " + i);
        }
    }

    public Object getQuestion() {
        Object obj = questions.get(index);
        index++;
        return obj;
    }
}
