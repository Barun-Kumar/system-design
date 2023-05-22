package com.model;

import com.exception.OccupiedCellExeception;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString

public class GameBoard {
    @Setter(AccessLevel.NONE)
    private int rowSize;
    @Setter(AccessLevel.NONE)
    private int colSize;
    private List<List<Cell>> board;
    private Cell currentMove;
    private Player currentPlayer;
    private boolean isWinnerFound=false;
    private List<Cell> strike;
    private int totalOccupiedCellCount=0;

    public GameBoard(int rowSize, int colSize){
        this.rowSize=rowSize;
        this.colSize=colSize;
        initializeBoard();
        printBoard();
    }

    /**
     * [
     *  [1,2,3],
     *  [4,5,6],
     *  [7,8,9]
     * ]
     */
    public List<List<Cell>> initializeBoard(){
        System.out.println("Initializing board with row :"+rowSize+ "and column :"+colSize);
        board = new ArrayList<List<Cell>>(rowSize);
        for(int row=0;row<rowSize;row++){
            List<Cell> columns =new ArrayList<Cell>(colSize);
            for(int col=0;col<colSize;col++){
                Cell cell = new Cell();
                columns.add(cell);
            }
            board.add(columns);
        }
        return board;
    }

    public void printBoard(){
        for (int row=0;row<rowSize;row++){
            List<Cell> rows= board.get(row);
            for (int col=0;col<colSize;col++){
                Cell colCell= rows.get(col);
                System.out.print(" | "+colCell.getSymbol());
            }
            System.out.println(" |");
        }
    }

    public void makeMove(Move move) throws Exception {
        if(move!=null && move.getCurrentMove()!=null){
            this.currentMove=move.getCurrentMove();
            this.currentPlayer=move.getCurrentPlayer();
            this.currentMove.setSymbol(currentPlayer.getPlayerSymbol());
            this.currentMove.setCellStat(CellStat.OCCUPIED);
            placeCurrentMoveOnBoard();
            printBoard();
        }else{
            System.out.println("Current move is null..., cant make move");
        }
    }

    private void placeCurrentMoveOnBoard() throws Exception {
        Cell cell =board.get(currentMove.getRow()).get(currentMove.getCol());
        if(cell.getCellStat().equals(CellStat.FREE) && totalOccupiedCellCount<=(rowSize*colSize)){
            cell.setCellStat(currentMove.getCellStat());
            cell.setRow(currentMove.getRow());
            cell.setCol(currentMove.getCol());
            cell.setSymbol(currentMove.getSymbol());
            this.totalOccupiedCellCount++;
        }else{
            throw new OccupiedCellExeception(500,"Cell is already occupied, cant make move on occupied cell");
        }
    }

    public void checkIfWinnerFound(){
        System.out.println("Checking if winner is found......");
        if(checkIfRowStrikes() || checkIfColumnStrikes() || checkIfDiagonalStrike() || checkIfReverseDiagonalStrike()){
            this.isWinnerFound=true;
        }
    }

    private boolean checkIfRowStrikes(){
        boolean isRowStrikes=true;
        int currentRow = currentMove.getRow();
        for(int i=0;i<colSize;i++){
            Cell cell = board.get(currentRow).get(i);
            if(cell.getCellStat().equals(CellStat.FREE) || !cell.getSymbol().equals(currentMove.getSymbol())){
                isRowStrikes=false;
                break;
            }
        }
        return isRowStrikes;
    }

    private boolean checkIfColumnStrikes(){
        boolean isColStrikes=true;
        int currentColumn= currentMove.getCol();
        for(int i=0;i<rowSize;i++){
            Cell cell = board.get(currentColumn).get(i);
            if(cell.getCellStat().equals(CellStat.FREE) || !cell.getSymbol().equals(currentMove.getSymbol())){
                isColStrikes=false;
                break;
            }
        }
        return isColStrikes;
    }

    private boolean checkIfDiagonalStrike(){
        boolean isDiagonalStrikes=true;
        for(int row=0, col=0; row<rowSize && col<colSize;row++,col++){
            if(row==col){
                Cell cell=board.get(row).get(col);
                if(cell.getCellStat().equals(CellStat.FREE) || !cell.getSymbol().equals(currentMove.getSymbol())){
                    isDiagonalStrikes=false;
                    break;
                }
            }
        }
        return isDiagonalStrikes;
    }

    private boolean checkIfReverseDiagonalStrike(){
        boolean isReverseDiagonalStrikes=true;
        int row=0;
        int col=colSize-1;
        for(;row<rowSize && col>=0;row++, col--){
            Cell cell=board.get(row).get(col);
            if(cell.getCellStat().equals(CellStat.FREE) || !cell.getSymbol().equals(currentMove.getSymbol())){
                isReverseDiagonalStrikes=false;
                break;
            }
        }
        return isReverseDiagonalStrikes;
    }
}
