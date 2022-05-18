package com.games.checkers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class CheckersGame {

    public static void main(String[] args) {
        Board gameBoard = new Board();
        List<Point> validStuff = gameBoard.checkValidMove(gameBoard.getCheckersArray()[2][0]);
        System.out.println(validStuff.toString());
        gameBoard.movePiece(gameBoard.getCheckersArray()[2][0],new Point(1,3));
        List<Point> validStuff2 = gameBoard.checkValidMove(gameBoard.getCheckersArray()[3][1]);
        gameBoard.movePiece(gameBoard.getCheckersArray()[3][1],new Point(2,4));
        List<Point> validStuff3 = gameBoard.checkValidMove(gameBoard.getCheckersArray()[5][3]);

        System.out.println("hi");

    }

}



