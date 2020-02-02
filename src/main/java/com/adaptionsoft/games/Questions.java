package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.Map;

import static com.adaptionsoft.games.Game.*;

public class Questions {
    Map<String, QuestionCategory> questionCategorys = new HashMap<>();
    public void init() {
        questionCategorys.put(POP, new QuestionCategory(POP));
        questionCategorys.put(SPORTS, new QuestionCategory(SPORTS));
        questionCategorys.put(SCIENCE, new QuestionCategory(SCIENCE));
        questionCategorys.put(ROCK, new QuestionCategory(ROCK));
    }

    public Object getQuestionsByPlace(int place) {
        QuestionCategory question = questionCategorys.get(currentCategory(place));
        if (question != null)
        {
            return question.getQuestion();
        }
        return null;
    }
    public String currentCategory(int place) {
        if ((place % 4) == 0) return POP;
        if ((place % 4) == 1) return SCIENCE;
        if ((place % 4) == 2) return SPORTS;
        return ROCK;
    }
}
