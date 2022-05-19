package com.games.checkers;


/*
 * Public class to represent a Checkers game
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public Board() {
        this.size = 8;
        this.checkersArray = new CheckerPiece[size][size];
        setupBoard();
    }

    // setupBoard method to check for valid board spaces, then populate initial checkers pieces in the checkerboard pattern starting from the first square at the top left of the board.
    // Following the placement of Player1's checkers pieces, it then completes Player2's pieces in the opposite pattern at the bottom.
    private void setupBoard() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y < 3 && isValidBoardSpace(x, y)) {
                    this.checkersArray[y][x] = new CheckerPiece(x, y, true);
                } else if (y >= size - 3 && isValidBoardSpace(x, y)) {
                    this.checkersArray[y][x] = new CheckerPiece(x, y, false);
                }
            }
        }
    }


    // isValidBoardSpace method to check if given board space is a black space usable by CheckerPiece. Standard checkerboards begin with white square in top left corner,
    // with int x, and int y acting as coordinates for each space.
    public boolean isValidBoardSpace(int x, int y) {
        // check for black space by checking if x returns an even integer when in an even row, or an odd integer when in an odd row.
        return x % 2 == y % 2;
    }

    // Method to get the x,y coordinates of a particular CheckerPiece object, then return the piece or null if empty.
    public CheckerPiece getLocationValue(int x, int y) {
        return this.checkersArray[x][y];
    }

    // Method to set a CheckerPiece object's x,y coordinates to the given coordinates.
    private void setLocationValue(int x, int y, CheckerPiece piece) {
        this.checkersArray[y][x] = piece;
    }

    public CheckerPiece[][] getCheckersArray() {
        return this.checkersArray;
    }

    //checks a checker piece's valid moves
    public List<Point> checkValidMove(CheckerPiece thePieceToCheck) {
        List<Point> validMoves = new ArrayList<>();
        int pieceX = (int) thePieceToCheck.getPieceLocation().getX();
        int pieceY = (int) thePieceToCheck.getPieceLocation().getY();
        int boardYBound = checkersArray[0].length;
        int boardXBound = checkersArray.length;
        boolean isBlack = thePieceToCheck.getIsBlack();

        Point upLeftMove = new Point(pieceX - 1, pieceY + 1);
        Point upRightMove = new Point(pieceX + 1, pieceY + 1);
        Point downLeftMove = new Point(pieceX - 1, pieceY - 1);
        Point downRightMove = new Point(pieceX + 1, pieceY - 1);

        //check up moves
        if (pieceY + 1 < boardYBound && (isBlack || thePieceToCheck.getIsKingPiece())) {
            //check right moves if black piece or kinged piece
            if (pieceX + 1 < boardXBound) {
                if (checkersArray[pieceY + 1][pieceX + 1] == null) validMoves.add(upRightMove);
                if (checkersArray[pieceY + 1][pieceX + 1] != null && validJumpCheckHelper(thePieceToCheck.getPieceLocation(), new Point(pieceX + 1, pieceY + 1))) {
                    validMoves.add(new Point(pieceX + 2, pieceY + 2));
                }

            }
            //check left moves
            if (pieceX - 1 >= 0) {
                if (checkersArray[pieceY + 1][pieceX - 1] == null) validMoves.add(upLeftMove);
                if (checkersArray[pieceY + 1][pieceX - 1] != null && validJumpCheckHelper(thePieceToCheck.getPieceLocation(), new Point(pieceX - 1, pieceY + 1))) {
                    validMoves.add(new Point(pieceX - 2, pieceY + 2));
                }

            }

        }
        //check down moves if it is white piece or kinged piece
        if (pieceY - 1 >= 0 && (!isBlack || thePieceToCheck.getIsKingPiece())) {
            //check right moves
            if (pieceX + 1 < boardXBound) {

                if (checkersArray[pieceY - 1][pieceX + 1] == null) validMoves.add(downRightMove);
                if (checkersArray[pieceY - 1][pieceX + 1] != null && validJumpCheckHelper(thePieceToCheck.getPieceLocation(), new Point(pieceX + 1, pieceY - 1))) {
                    validMoves.add(new Point(pieceX + 2, pieceY - 2));
                }

            }
            //check left moves
            if (pieceX - 1 >= 0) {
                if (checkersArray[pieceY - 1][pieceX - 1] == null) validMoves.add(downLeftMove);
                //found another piece use helper to check if can jump then add the jump location
                if (checkersArray[pieceY - 1][pieceX - 1] != null && validJumpCheckHelper(thePieceToCheck.getPieceLocation(), new Point(pieceX - 1, pieceY - 1))) {
                    validMoves.add(new Point(pieceX - 2, pieceY - 2));
                }
            }

        }


        return validMoves;
    }

    private boolean validJumpCheckHelper(Point thePointToJumpFrom, Point thePointToJumpOver) {
        boolean solution = false;

        //check to make sure pieces at the points are not the same color
        if (checkersArray[(int) thePointToJumpFrom.getY()][(int) thePointToJumpFrom.getX()].getIsBlack() != checkersArray[(int) thePointToJumpOver.getY()][(int) thePointToJumpOver.getX()].getIsBlack()) {
            int xDirection = (int) thePointToJumpOver.getX() - (int) thePointToJumpFrom.getX();
            int yDirection = (int) thePointToJumpOver.getY() - (int) thePointToJumpFrom.getY();
            //check to make sure the piece after thePointToJumpOver is within bounds and empty
            if ((int) thePointToJumpOver.getX() + xDirection < checkersArray.length && (int) thePointToJumpOver.getX() + xDirection >= 0
                    && (int) thePointToJumpOver.getY() + yDirection < checkersArray[0].length && (int) thePointToJumpOver.getY() + yDirection >= 0
                    && checkersArray[(int) thePointToJumpOver.getY() + yDirection][(int) thePointToJumpOver.getX() + xDirection] == null) {
                solution = true;
            }
        }

        return solution;
    }

    public void movePiece(CheckerPiece thePiece, Point thePoint) {
        List<Point> validMoves = checkValidMove(thePiece);
        //make int copies for readability
        int currentPieceX = (int) thePiece.getPieceLocation().getX();
        int currentPieceY = (int) thePiece.getPieceLocation().getY();
        int pointX = (int) thePoint.getX();
        int pointY = (int) thePoint.getY();
        //check if intended move is valid
        if (validMoves.contains(thePoint)) {
            //check if moving is a jump
            if (pointX - currentPieceX == 2 || pointX - currentPieceX == -2) {
                checkersArray[(currentPieceY + pointY) / 2][(currentPieceX + pointX) / 2] = null;
            }

            //move the piece
            checkersArray[pointY][pointX] = thePiece;
            //update piece data
            thePiece.getPieceLocation().setLocation(pointX, pointY);
            //check king status
            thePiece.checkKingStatus(checkersArray);
            //delete old piece
            checkersArray[currentPieceY][currentPieceX] = null;

        }
    }


}