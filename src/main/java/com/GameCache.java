package com;

import com.model.Game;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class GameCache {
    @Setter(AccessLevel.NONE)
    private Map<String , Game> gameMap = null;
    @Setter(AccessLevel.NONE)
    private Game currentGame;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static GameCache gameCache;
    private GameCache(){
        gameMap = new HashMap<>();
        currentGame=null;
    }
    public static GameCache getInstance(){
        if(gameCache==null){
            synchronized (GameCache.class){
                if(gameCache==null) {
                    gameCache = new GameCache();
                }
            }
        }
        return gameCache;
    }

    public Game addGame(Game game){
        if(gameMap==null){
            getInstance();
            gameMap.putIfAbsent(game.getGameId(),game);
            currentGame=game;
        }
        return game;
    }
}
