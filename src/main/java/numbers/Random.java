package main.java.numbers;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public Random() {
    }

    /** Rives a random int from the range given, including the range numbers */
    public int random(int startVal, int endVal) {
        return ThreadLocalRandom.current().nextInt(startVal, endVal + 1);
    }
}
