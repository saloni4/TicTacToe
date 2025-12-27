package org.example.Model;

import java.util.ArrayList;
import java.util.*;

public class Board {
    public int size;
    public PlayingPiece[] [] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece piece){
        if(row>=board.length || col>=board[0].length)
            return false;
        else if(board[row][col]==null){
            board[row][col]=piece;
            return true;
        }
        else
        return false;
    }

    public void printBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType + " ");
                } else {
                    System.out.print("  ");

                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<int[]> getFreeCells(){
        List<int[]> res=new ArrayList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==null){
                    res.add(new int[] {i,j});
                }
            }
        }
        return res;
    }
}
