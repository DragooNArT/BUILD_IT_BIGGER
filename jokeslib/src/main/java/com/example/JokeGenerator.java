package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeGenerator {

    private static JokeGenerator instance = null;
    private int jokeCount;
    private static List<String> jokesList = new ArrayList<String>();
    Random rand = new Random();
    static {

        jokesList.add("Joke1");
        jokesList.add("Joke2");
        jokesList.add("Joke3");
    }
    private JokeGenerator() {
     //singleton
    }

    public static JokeGenerator getInstance() {
        if(instance == null) {
            instance = new JokeGenerator();
        }
        return instance;
    }

    public static boolean isStringJoke(String joke) {
       return jokesList.contains(joke);
    }
    public int getJokeCount() {
        return jokeCount;
    }

    public String generateJoke() {
        jokeCount++;
        return jokesList.get(rand.nextInt(jokesList.size()));
    }
}
