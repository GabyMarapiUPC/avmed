package com.example.avmed.util;

import java.util.Random;

public class GeneratorUtil {

    public static String generateRandom(){
        Random random = new Random();
        int num = random.nextInt(1000);
        return String.format("%04d", num);
    }
}
