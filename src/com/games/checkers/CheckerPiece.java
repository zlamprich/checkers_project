package com.games.checkers;

import java.util.ArrayList;
import java.util.Arrays;


/*
 * Public class to handle the functions of all Checkers game pieces.
 */

public class CheckerPiece {
    // private vars
    private int x;
    private int y;
    private boolean isKingPiece = false;
    private boolean isBlack;


        /* Constructor to check CheckersPiece coordinates, and then
         * check boolean true/false to see if piece is Black/P1 (true) or White/P2 (false)
         */
        public CheckerPiece(int x, int y, boolean b) {
            this.x = x;
            this.y = y;
            this.isBlack = false;
        }

        //method to get the location of a given piece on the board, returned as an array of X, Y coordinates.
        public int [] getPieceLocation() {
            int[] coordinates = new int[2];
            coordinates[0] = this.x;
            coordinates[1] = this.y;
            return coordinates;
        }

        //method to set piece as a king piece once reaching end of the board.
        private void setAsKing() {
            isKingPiece = true;
        }

        //method to CHECK if piece is a King piece after reaching the end of the board. Ran after every move.
        public void checkKingStatus(Board board) {
            if (isBlack && this.y == board.size - 1 || !isBlack && this.y == 0)
                this.setAsKing();
        }

        // method to return a string of attributes related to a given piece. Checks for color, and king status.
        public String getAttributes() {
            String attributes;

            if (isBlack)
                attributes = "Black";
            else
                attributes = "White";
            if (isKingPiece)
                attributes += "King";
            else
                attributes += "Non-King";

            return attributes;
        }
    }
