package com.controller;

import com.exception.GameValidationException;
import com.model.Game;
import com.model.GameRequest;
import com.model.MoveRequest;
import com.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;
    @PostMapping("/createGame")
    public Game createGame(@RequestBody GameRequest gameRequest) throws GameValidationException {
        return gameService.createGame(gameRequest);
    }

    @PostMapping("/makeMove")
    public ResponseEntity makeMove(@RequestBody MoveRequest moveRequest){
        try {
            Game game =gameService.handleMove(moveRequest);
            if(game.getBoard().isWinnerFound()){
                return new ResponseEntity<>("Game ends the winner is"+game.getBoard().getCurrentPlayer(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Move made successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}
