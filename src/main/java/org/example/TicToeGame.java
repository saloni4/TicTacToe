package org.example;

import org.example.Model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicToeGame {
    Deque<Player> players;
    Board gameBoard;

    public static void main(String[] args) {
        TicToeGame game = new TicToeGame();
        game.initializeGame();
        System.out.println("game winner is: " + game.startGame());
    }

    private  void initializeGame() {
        gameBoard=new Board(3);

        players=new LinkedList<>();
        PieceTypeX cross=new PieceTypeX(PieceType.X);
        PieceTypeO noughts=new PieceTypeO(PieceType.O);
        players.add(new Player("player1",cross));
        players.add(new Player("player2",noughts));
    }


    private String startGame() {
        boolean noWinner=true;
        while(noWinner){
            Player playerTurn =players.removeFirst();
            gameBoard.printBoard();
            System.out.println("Player: "+ playerTurn.getName() + " enter row, column: ");
            Scanner input=new Scanner(System.in);
            String str=input.nextLine();
            String[] s=str.split(",");
            int row=Integer.parseInt(s[0]);
            int col=Integer.parseInt(s[1]);

            boolean addedpiece= gameBoard.addPiece(row,col,playerTurn.getPlayingPiece());
            if(!addedpiece){
                System.out.println("incorrect position , pls try again!");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);
            boolean isWinner= isWinner(row, col, playerTurn.getPlayingPiece().pieceType);
            if(isWinner)
                return playerTurn.getName();

        }
        return "tie";
    }

    private boolean isWinner(int row, int col, PieceType pieceType) {
        boolean rowMatch=true;
        boolean colMatch=true;
        boolean diagonalMatch=true;
        boolean antidiagonalMatch=true;

        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType!=pieceType )
               rowMatch=false;
        }
        for(int i=0;i<gameBoard.size;i++){
            if(gameBoard.board[i][col]==null || gameBoard.board[i][col].pieceType!=pieceType )
                colMatch=false;
        }
        for(int i=0,j=0;i<gameBoard.size;i++,j++){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType!=pieceType )
                diagonalMatch=false;
        }
        for(int i=0,j= gameBoard.size-1;i<gameBoard.size;i++,j--){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType!=pieceType )
                antidiagonalMatch=false;
        }
        return rowMatch||colMatch||diagonalMatch||antidiagonalMatch;
    }

}