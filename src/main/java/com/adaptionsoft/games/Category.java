package com.adaptionsoft.games;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    BLUES("Blues"),
    HISTORY("History");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public static String currentCategory(int places) {
        Category[] array = Category.values();
        int size = array.length;
        return array[places % size].name;
    }

    public String getName()
    {
        return this.name;
    }
}
