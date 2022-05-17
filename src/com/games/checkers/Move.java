package com.games.checkers;

import java.util.Arrays;
import java.util.ArrayList;

public class Move {
    // vars representing move functions
    int x1,y1;
    int x2,y2;
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
}