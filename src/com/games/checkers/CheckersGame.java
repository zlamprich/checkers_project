package com.games.checkers;

import com.apps.util.Prompter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CheckersGame {
    private static Board board;
    private static Player player_1;
    private static Player player_2;
    public static void main(String[] args) throws IOException {
        promptWelcome();
        startGame();
        System.out.println();

    }
    private static void promptWelcome() throws IOException {
        Path rulesPath = Path.of("src/coms/games/resources/Rules");
        String rulesString =Files.readString(rulesPath);
        System.out.println(rulesString);
    }
    private static void startGame() throws IOException {
        Prompter prompter =new Prompter(new Scanner(System.in));
        String startGame =prompter.prompt("Start the game? y/n...  ","[yn]","Invalid Response please enter y or n");

        board = new Board();
        drawBoard(board.getCheckersArray());

    }

    private static void drawBoard(CheckerPiece[][] theBoardArray) throws IOException {
        Path bluePiece = Path.of("src/coms/games/resources/BlueSquare");
        Path redPiece = Path.of("src/coms/games/resources/RedSquare");
        Path emptySquarePath = Path.of("src/coms/games/resources/DefaultSquareEmpty");
        Path filledSquarePath = Path.of("src/coms/games/resources/DefaultSquareFilled");
        Path bottomRow = Path.of("src/coms/games/resources/bottomRowBoard");
        int rowBoarderIndex = 0;
        Path currentRowBorder = Path.of("src/coms/games/resources/number_" + rowBoarderIndex);
        //draw board starting from top left corner lean on reference for placement
        for (int y = theBoardArray.length - 1; y >= 0; y--) {
            //change rowBorderIndex to reflect row
            rowBoarderIndex = y;
            currentRowBorder = Path.of("src/coms/games/resources/number_" + rowBoarderIndex);

            //Initialize Readers here
            //gross implementation but lets you reference correct line as you're going thru the loop multiple times
            BufferedReader border = Files.newBufferedReader(currentRowBorder);
            BufferedReader redSquare = Files.newBufferedReader(redPiece);
            BufferedReader blueSquare = Files.newBufferedReader(bluePiece);
            BufferedReader emptySquare = Files.newBufferedReader(emptySquarePath);
            BufferedReader filledSquare = Files.newBufferedReader(filledSquarePath);



            //draw entire row four times
            for (int textRow = 0; textRow < 4; textRow++) {
                //Strings to hold line data
                String currentRedSquareLine = redSquare.readLine();
                String currentBlueSquareLine = blueSquare.readLine();
                String currentEmptySquareLine = emptySquare.readLine();
                String filledSquareLine = filledSquare.readLine();


                //triple nest super bad practice but every cell has four lines of text to represent it
                for (int x = 0; x < theBoardArray[0].length; x++) {
                    //if on the first cell draw the border first
                    if (x == 0) {
                        String line = border.readLine();
                        if (line != null) {
                            System.out.print(line);
                        }
                    }

                    //if odd row start with empty space if even row fill with what is there by checking the passed array
                    if (y % 2 == 1) {
                        //if even column on odd row fill empty space
                        if (x % 2 == 0 && currentEmptySquareLine != null) {
                            System.out.print(currentEmptySquareLine);
                        }
                        //if odd column on odd row check array and fill accordingly
                        if (x % 2 == 1) {
                            //nothing there fill square
                            if (theBoardArray[y][x] == null && filledSquareLine != null) {
                                System.out.print(filledSquareLine);
                            }
                            //isBluePlayerPiece
                            else if (theBoardArray[y][x].getIsBlack() == true && currentEmptySquareLine != null) {
                                System.out.print(currentBlueSquareLine);
                            }
                            //isRedPlayerPiece
                            else if (theBoardArray[y][x].getIsBlack() == false && currentBlueSquareLine != null) {
                                System.out.print(currentRedSquareLine);
                            }
                        }

                    }
                    if (y % 2 == 0) {
                        //if odd column on  even row fill empty space
                        if (x % 2 == 1 && currentEmptySquareLine != null) {
                            System.out.print(currentEmptySquareLine);
                        }
                        //if odd column on odd row check array and fill accordingly
                        if (x % 2 == 0) {
                            //nothing there fill square
                            if (theBoardArray[y][x] == null && filledSquareLine != null) {
                                System.out.print(filledSquareLine);
                            }
                            //isBluePlayerPiece
                            else if (theBoardArray[y][x].getIsBlack() == true && currentEmptySquareLine != null) {
                                System.out.print(currentBlueSquareLine);
                            }
                            //isRedPlayerPiece
                            else if (theBoardArray[y][x].getIsBlack() == false && currentBlueSquareLine != null) {
                                System.out.print(currentRedSquareLine);
                            }
                        }

                    }

                }
                //end of row sout next line
                System.out.println();
            }
            //close readers
            border.close();
            blueSquare.close();
            redSquare.close();
            emptySquare.close();
            filledSquare.close();


        }
        //bottom of board draw bottom from file
        String bottom =Files.readString(bottomRow);
        System.out.println(bottom);
    }

}



