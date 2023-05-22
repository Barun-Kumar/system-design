package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cell {
    private int row;
    private int col;
    private String symbol ="-";
    private CellStat cellStat = CellStat.FREE;

    public Cell(int row, int col){
        this.row=row;
        this.col=col;
    }
}
