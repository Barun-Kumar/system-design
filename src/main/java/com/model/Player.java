package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Player {

    private String playerId;
    private String name;
    private String playerSymbol;
    private int score;


}
