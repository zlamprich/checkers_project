package com.games.checkers;


public class Board {
    /* static final integers for new Board creation
     * The standard checkers board size is 8x8, referenced in rows/columns vars.
     */

    public int size;
    public CheckerPiece[][] checkersArray;


    public Board(int size) {
        this.checkersArray = new CheckerPiece[size][size];
        this.size = size;

        setupBoard();
    }


    public void setupBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y < 3 && isValidBoardSpace(x, y)) {
                    this.checkersArray[y][x] = new CheckerPiece(x, y, true);
                }

                else if ( y >= size - 3 && isValidBoardSpace(x,y))
                {
                    this.checkersArray[y][x] = new CheckerPiece(x,y, false);
                }
            }
        }
    }


    public boolean isValidBoardSpace (int x, int y) {
        return x % 2 == y % 2;
    }


}