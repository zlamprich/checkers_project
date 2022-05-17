package com.games.checkers;

import java.util.Arrays;
import java.util.ArrayList;

public class Move {
    // vars representing move functions
    int x1,y1;
    int x2,y2;
    Move previousMove;
    boolean jumpMove;

    /*
     * Ctor for Move objects. Initializes starting position through x1,y1 coords
     * and initializes the end position coordinates through x2, y2.
     * boolean jumpMove checks to see if piece jumps another.
     */

    public Move(int x1, int y1, int x2, int y2, boolean jumpMove) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.jumpMove = jumpMove;
    }

    // Method to get piece starting position as coordinates in an array.
    public int[] getStartingCoords() {
        int[] startPosition = new int[2];
        startPosition[0] = x1;
        startPosition[1] = y1;
        return startPosition;
    }

    //Method to get piece end position as coordinates in an array.
    public int[] getEndingCoords() {
        int[] endPosition = new int[2];
        endPosition[0] = x2;
        endPosition[1] = y2;
        return endPosition;
    }

    public CheckerPiece[] getJumpedPieces(Board board) {
        //bool to check if move was a jump move
        if (jumpMove) {
            //creates an arraylist of pieces.
            ArrayList<CheckerPiece> pieces = new ArrayList<>();

            //Check to see if a different piece was located in between start and end coordinates of move, resulting in a jump
            int piecesX = (x1 + x2)/2;
            int piecesY = (y1 + y2)/2;

            //Add jump result to board without grabbing pieces from board
            pieces.add(board.getLocationValue(piecesX, piecesY));

            //Go back to get jumped pieces that were located in between x,y coords.
            if (previousMove != null) {
                pieces.addAll(Arrays.asList(previousMove.getJumpedPieces(board)));
            }
            //Trim array results
            pieces.trimToSize();
            return pieces.toArray(new CheckerPiece[1]);
        }
        else
            return null;
    }
}