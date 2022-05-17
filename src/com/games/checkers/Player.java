package com.games.checkers;

import java.util.ArrayList;
import java.util.Scanner;

class Player {

//Getting player names and reading them with scanner
    public static void Players(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1: Please enter your name");
        String P1 = scanner.nextLine();
        System.out.println("Player 2: Please enter your name");
        String P2 = scanner.nextLine();
        }

    public static void main(String[] args) {
        Players();
    }
}