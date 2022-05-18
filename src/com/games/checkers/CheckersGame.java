package com.games.checkers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class CheckersGame {

    public static void main(String[] args) {
        Board gameBoard = new Board(8);
        List<Point> validStuff = gameBoard.checkValidMove(gameBoard.getCheckersArray()[2][0]);
        System.out.println(validStuff.toString());
        System.out.println("hi");

    }

}



