package com.respository;

import com.GameCache;
import com.exception.GameValidationException;
import com.model.Game;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@ToString
@Repository
public class GameRepository {

    public Game getGameById(String id){
        return GameCache.getInstance().getGameMap().get(id);
    }

    public Game getCurrentGame(){
        return GameCache.getInstance().getCurrentGame();
    }
    public Game addGame(Game game) throws GameValidationException {
       if(game==null){
           throw new GameValidationException(500, "Cant add empty game in the cache");
       }else{
           return GameCache.getInstance().addGame(game);
       }
    }

}
