package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameRequest {
    private int boardRowSize;
    private int boardColumnSize;
    private int numberOfPlayers;
    List<Player> players;
}
