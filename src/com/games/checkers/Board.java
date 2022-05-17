package com.games.checkers;


/*
 * Public class to represent a Checkers game
 */

public class Board {
    /* static final integers for new Board creation
     * The standard checkers board size is 8x8, referenced in size var.
     */

    public int size;
    public CheckerPiece[][] checkersArray;



    /* Generates an empty checkersArray board , then store the 8 by 8 board in the
     * array once initialized. setupBoard method ran to then set up the board following
     * initialization.
     */

    public Board(int size) {
        this.checkersArray = new CheckerPiece[size][size];
        this.size = size;

        setupBoard();
    }

    // setupBoard method to check for valid board spaces, then populate initial checkers pieces in the checkerboard pattern starting from the first square at the top left of the board.
    // Following the placement of Player1's checkers pieces, it then completes Player2's pieces in the opposite pattern at the bottom.
    public void setupBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y < 3 && isValidBoardSpace(x, y))  {
                    this.checkersArray[y][x] = new CheckerPiece(x, y, true);
                }

                else if ( y >= size - 3 && isValidBoardSpace(x,y))
                {
                    this.checkersArray[y][x] = new CheckerPiece(x,y, false);
                }
            }
        }
    }


    // isValidBoardSpace method to check if given board space is a black space usable by CheckerPiece. Standard checkerboards begin with white square in top left corner,
    // with int x, and int y acting as coordinates for each space.
    public boolean isValidBoardSpace (int x, int y) {
        // check for black space by checking if x returns an even integer when in an even row, or an odd integer when in an odd row.
        return x % 2 == y % 2;
    }

    // Method to get the x,y coordinates of a particular CheckerPiece object, then return the piece or null if empty.
    public CheckerPiece getLocationValue(int x, int y) {
        return this.checkersArray[y][x];
    }

    // Method to set a CheckerPiece object's x,y coordinates to the given coordinates.
    private void setLocationValue(int x, int y, CheckerPiece piece) {
        this.checkersArray[y][x] = piece;
    }


}