package com.adaptionsoft.games;

import java.util.HashMap;
import java.util.Map;


public class Questions {
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    public static final String BLUES = "Blues";
    public static final String HISTORY = "History";
    public static final int CATEGORY_NUM = 4;
    Map<String, QuestionCategory> questionCategorys = new HashMap<>();
    public void init() {
        questionCategorys.put(POP, new QuestionCategory(POP));
        questionCategorys.put(SPORTS, new QuestionCategory(SPORTS));
        questionCategorys.put(SCIENCE, new QuestionCategory(SCIENCE));
        questionCategorys.put(ROCK, new QuestionCategory(ROCK));
        questionCategorys.put(BLUES, new QuestionCategory(BLUES));
        questionCategorys.put(HISTORY, new QuestionCategory(HISTORY));
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
        if ((place % CATEGORY_NUM) == 0) return POP;
        if ((place % CATEGORY_NUM) == 1) return SCIENCE;
        if ((place % CATEGORY_NUM) == 2) return SPORTS;
        if ((place % CATEGORY_NUM) == 3) return ROCK;
        if ((place % CATEGORY_NUM) == 4) return BLUES;
        if ((place % CATEGORY_NUM) == 5) return HISTORY;
        return null;
    }
}
