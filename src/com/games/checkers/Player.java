package com.games.checkers;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Scanner;

class Player {


    public static String  playerName1;
    public static String  playerName2;


    //Getting player names and reading them with scanner
    public static void Players() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1: Please enter your name");
        String p1 = scanner.nextLine();


        System.out.println("Player 2: Please enter your name");
        String p2 = scanner.nextLine();


    }
}