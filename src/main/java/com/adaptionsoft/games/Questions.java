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
    public static final int CATEGORY_NUM = 6;
    Map<String, QuestionCategory> questionCategorys = new HashMap<>();
    public void init() {
        questionCategorys.put(POP, new QuestionCategory(POP));
        questionCategorys.put(SPORTS, new QuestionCategory(SPORTS));
        questionCategorys.put(SCIENCE, new QuestionCategory(SCIENCE));
        questionCategorys.put(ROCK, new QuestionCategory(ROCK));
        questionCategorys.put(BLUES, new QuestionCategory(BLUES));
        questionCategorys.put(HISTORY, new QuestionCategory(HISTORY));
    }

    public Object getQuestionsByPlace(int place) throws Exception{
        QuestionCategory question = questionCategorys.get(currentCategory(place));
        if (question != null)
        {
            return question.getQuestion();
        }
        throw new Exception("unknow question category");
    }
    public String currentCategory(int place) {
        switch (place % CATEGORY_NUM)
        {
            case 1: return SCIENCE;
            case 2: return SPORTS;
            case 3: return ROCK;
            case 4: return BLUES;
            case 5: return HISTORY;
            default: return POP;
        }
    }
}
