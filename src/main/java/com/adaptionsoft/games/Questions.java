package com.adaptionsoft.games;

import java.util.LinkedList;

import static com.adaptionsoft.games.Game.*;

public class Questions {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    public void init() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public Object getQuestionsByPlace(int place) {
        Object obj = null;
        if (currentCategory(place) == POP)
        {
            obj = popQuestions.removeFirst();
        }
        if (currentCategory(place) == SCIENCE)
            obj = scienceQuestions.removeFirst();
        if (currentCategory(place) == SPORTS)
            obj = sportsQuestions.removeFirst();
        if (currentCategory(place) == ROCK)
            obj = rockQuestions.removeFirst();
        return obj;
    }
    public String currentCategory(int place) {
        if ((place % 4) == 0) return POP;
        if ((place % 4) == 1) return SCIENCE;
        if ((place % 4) == 2) return SPORTS;
        return ROCK;
    }
}
