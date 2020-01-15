package com.nafkhanzam.utils;

import java.util.Random;

public class RandomUtils {

    public static int random(int from, int to) {
        Random r = new Random();
        return r.nextInt(to - from + 1) + from;
    }
}