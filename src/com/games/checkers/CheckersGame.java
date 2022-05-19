package com.games.checkers;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class CheckersGame {
    private static Board board;
    private static Player player_1;
    private static Player player_2;

    public static void main(String[] args) throws IOException {
        //setup methods ran once
        promptWelcome();
        startGame();
        setupPlayers();
        //loop for game checking if either player has lost yet
        boolean gameOver = false;
        while (!gameOver) {

            //p1 move
            //check if player has lost before allowing them to move
            if (player_1.thisPlayerLost() == false ) {
                playerMove(player_1);
                player_1.updateBoard(board);
            }
            //player has lost
            else {
                System.out.println(player_2.toString() + " wins!!!");
                gameOver = true;
            }

            //p2 move

            if (player_2.thisPlayerLost() == false && !gameOver) {
                playerMove(player_2);
                player_2.updateBoard(board);
            } else if (!gameOver){
                System.out.println(player_1.toString() + " wins!!!");
                gameOver = true;
            }
        }

    }

    private static void playerMove(Player thePlayer) {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String chooseCords = prompter.prompt(thePlayer.toString() + " choose p" +
                "iece to move.Example row,column...0,0  ", "[0|1|2|3|4|5|6|7],[0|1|2|3|4|5|6|7]", "Invalid Response please enter in the digit form of row,column");
        int xCord = Integer.parseInt(chooseCords.split(",")[0]);
        int yCord = Integer.parseInt(chooseCords.split(",")[1]);
        //check if piece exists and is the right player's piece
        if (board.getLocationValue(xCord, yCord) != null && board.getLocationValue(xCord, yCord).getIsBlack() == thePlayer.isBlue()) {
            //list available moves for valid piece
            System.out.println("Selected piece can move in the following places ");
            //loop thru avail move options
            int counter = 0;
            String regexCounter = "[";
            //check if a valid move even exist
            List<Point> availablePoints = board.checkValidMove(board.getLocationValue(xCord, yCord));
            if (!availablePoints.isEmpty()) {
                for (Point point :
                        availablePoints) {
                    System.out.println(counter + ". row = " + point.y + " column =" + point.x);
                    regexCounter += counter + "|";
                    counter++;
                }
                regexCounter = regexCounter.substring(0, regexCounter.length() - 1) + "]";
            } else {
                System.out.println("selected piece cannot move try again...");
                playerMove(thePlayer);
            }

            //trim the end of the regexCounter

            String validMoves = prompter.prompt("Enter number choice to move to.", regexCounter, "Invalid choice please choose one of the following digits" + regexCounter);
            board.movePiece(board.getLocationValue(xCord, yCord), availablePoints.get(Integer.valueOf(validMoves)));
            try {

                drawBoard(board.getCheckersArray());
                Console.clear();


            } catch (IOException e) {
                System.out.println("failed to draw");
                e.printStackTrace();
            }
        } else {
            System.out.println("not a valid piece selection try again....");
            playerMove(thePlayer);
        }


    }

    private static void promptWelcome() throws IOException {
        Path rulesPath = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\Rules");
        String rulesString = Files.readString(rulesPath);
        System.out.println(rulesString);
    }

    private static void startGame() throws IOException {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String startGame = prompter.prompt("Start the game? y/n...  ", "[yn]", "Invalid Response please enter y or n");
        if (startGame.equalsIgnoreCase("y")) {
            board = new Board();
            drawBoard(board.getCheckersArray());

        } else {
            System.out.println("continue reading rules...");
            startGame();
        }

    }

    private static void setupPlayers() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        String setupPlayer1 = prompter.prompt("\u001B[36mPlayer 1\u001B[0m enter your name or leave blank for default");
        if (setupPlayer1.isEmpty()) {
            player_1 = new Player(board, true);
        } else {
            player_1 = new Player(board, true, setupPlayer1);
        }
        System.out.println(player_1.toString() + " has been created");
        String setupPlayer2 = prompter.prompt("\u001B[91mPlayer 2\u001B[0m enter your name or leave blank for default");
        if (setupPlayer2.isEmpty()) {
            player_2 = new Player(board, false);
        } else {
            player_2 = new Player(board, false, setupPlayer2);
        }
        System.out.println(player_2.toString() + " has been created");
    }

    private static void drawBoard(CheckerPiece[][] theBoardArray) throws IOException {
        Path bluePiece = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\BlueSquare");
        Path redPiece = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\RedSquare");
        Path emptySquarePath = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\DefaultSquareEmpty");
        Path filledSquarePath = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\DefaultSquareFilled");
        Path bottomRow = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\bottomRowBoard");
        int rowBoarderIndex = 0;
        Path currentRowBorder = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\number_" + rowBoarderIndex);
        //draw board starting from top left corner lean on reference for placement
        for (int y = theBoardArray.length - 1; y >= 0; y--) {
            //change rowBorderIndex to reflect row
            rowBoarderIndex = y;
            currentRowBorder = Path.of("C:\\AmazonSDE\\Git\\StudentWork\\MiniProject\\checkers_project\\resources\\number_" + rowBoarderIndex);

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
        String bottom = Files.readString(bottomRow);
        System.out.println(bottom);
    }

}



