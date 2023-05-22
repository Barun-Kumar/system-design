package com.model;

import com.util.Constant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString

public class Game {
    private String gameId;
    private GameBoard board;
    //Who all are playing the games
    Map<String,Player> mapOfPlayers;
    GameState gameState;
    List<Move> moves;


    public Game initializeGame(GameRequest gameRequest){
        int row= gameRequest.getBoardRowSize()>1?gameRequest.getBoardRowSize():Constant.DEFAULT_BOARD_ROW;
        int column = gameRequest.getBoardColumnSize()>1?gameRequest.getBoardColumnSize():Constant.DEFAULT_BOARD_COLUMN;
        mapOfPlayers = gameRequest.getPlayers().stream().collect(
                Collectors.toMap(Player::getPlayerId,player ->player));
        this.board=new GameBoard(row,column);
        this.gameState=null;
        this.gameId=null;
        return this;
    }


}
