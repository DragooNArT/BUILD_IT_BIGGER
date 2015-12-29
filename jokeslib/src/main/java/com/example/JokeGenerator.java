package com.example;

public class JokeGenerator {

    private static JokeGenerator instance = null;
    private int jokeCount;
    private String lastJoke;
    private JokeGenerator() {
     //singleton
    }

    public static JokeGenerator getInstance() {
        if(instance == null) {
            instance = new JokeGenerator();
        }
        return instance;
    }

    public String getLastJoke() {
        return lastJoke;
    }
    public int getJokeCount() {
        return jokeCount;
    }

    public String generateJoke() {
        jokeCount++;
        return "random joke generated";
    }
}
