package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MoveRequest {
    private String gameId;
    private String playerId;
    private int row;
    private int col;
}
