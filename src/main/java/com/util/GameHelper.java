package com.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameHelper {


    public static String generateGameId(){
        Random random = new Random();
        int gameId=random.nextInt(1000000);
        return gameId+"";
    }
}
