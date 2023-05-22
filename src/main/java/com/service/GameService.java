package com.service;
import com.exception.GameValidationException;
import com.exception.GameDoesNotExist;
import com.model.*;
import com.respository.GameRepository;
import com.util.GameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;



@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public Game createGame(GameRequest gameRequest) throws GameValidationException {
        System.out.println("Creating game gameRequest :"+gameRequest);
        Game game = new Game().initializeGame(gameRequest);
        game.setGameId(GameHelper.generateGameId());
        gameRepository.addGame(game);
        System.out.println("New game Id :"+game.getGameId());
        return game;
    }

    public Game handleMove(MoveRequest moveRequest) throws Exception {
        System.out.println("Handling move, request :"+moveRequest);
        Move move = prepareMove(moveRequest);
        Game game=gameRepository.getCurrentGame();
        game.getBoard().makeMove(move);
        game.getBoard().checkIfWinnerFound();
        if(game.getBoard().isWinnerFound()){
            game.setGameState(GameState.completed);
            System.out.println("Game is completed, winner found, creating another game");
            GameRequest gameRequest=buildGameRequest(game);
            game=createGame(gameRequest);
        }
        else if(game.getBoard().getTotalOccupiedCellCount()==game.getBoard().getRowSize()*game.getBoard().getColSize()){
            if(!game.getBoard().isWinnerFound()){
                game.setGameState(GameState.tie);
                System.out.println("Game is tie, no winner, creating another game");
                GameRequest gameRequest=buildGameRequest(game);
                game=createGame(gameRequest);
            }
        }
        return game;
    }

    /**
     * Build GameRequest with old game
     * @param oldGame
     * @return
     */
        public GameRequest buildGameRequest(Game oldGame){
            System.out.println("Building new game with old request");
            GameRequest gameRequest = new GameRequest();
            gameRequest.setPlayers(oldGame.getMapOfPlayers().values().stream().collect(Collectors.toList()));
            gameRequest.setBoardRowSize(oldGame.getBoard().getRowSize());
            gameRequest.setBoardColumnSize(oldGame.getBoard().getColSize());
            System.out.println(gameRequest);
            return gameRequest;
        }

    private Move prepareMove(MoveRequest moveRequest) throws GameDoesNotExist, GameValidationException {
        System.out.println("Preparing move..");
        String playerId=moveRequest.getPlayerId();
        Game game=gameRepository.getCurrentGame();
        game=validateGame(game,moveRequest.getGameId());
        Player currentPlayer=game.getMapOfPlayers().get(moveRequest.getPlayerId());
        Move move = new Move();
        move.setCurrentPlayer(currentPlayer);
        move.setCurrentMove(new Cell(moveRequest.getRow(),moveRequest.getCol()));
        return move;
    }

    private Game validateGame(Game currentGame,String gameId) throws GameValidationException, GameDoesNotExist {
        if(currentGame==null){
            currentGame=gameRepository.getCurrentGame();
            if(currentGame==null){
                currentGame=gameRepository.getGameById(gameId);
            }
        }

        if(currentGame.getGameId().trim().equals(gameRepository.getGameById(gameId).getGameId().trim())){
            return currentGame;
        }else{
            throw new GameValidationException(500,"Current game and request gameId does not match");
        }
    }


}
