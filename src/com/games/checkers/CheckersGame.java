package com.games.checkers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.apps.util.Prompter;
import java.util.Scanner;

class CheckersGame extends Board {

    public static void main(String[] args) {
        Board gameBoard = new Board();
        List<Point> validStuff = gameBoard.checkValidMove(gameBoard.getCheckersArray()[2][0]);
        System.out.println(validStuff.toString());
        gameBoard.movePiece(gameBoard.getCheckersArray()[2][0],new Point(1,3));
        List<Point> validStuff2 = gameBoard.checkValidMove(gameBoard.getCheckersArray()[3][1]);
        gameBoard.movePiece(gameBoard.getCheckersArray()[3][1],new Point(2,4));
        List<Point> validStuff3 = gameBoard.checkValidMove(gameBoard.getCheckersArray()[5][3]);

        System.out.println("hi");





        Prompter prompter = new Prompter(new Scanner(System.in));
        String name = prompter.prompt("Please enter your name: ");
        String color = prompter.prompt("Please enter your piece color: ");




        Player Player1 = new Player(name, color);
        System.out.println("Player 1 is: " + Player1);


    }



}



