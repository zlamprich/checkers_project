package com.games.checkers;


import java.util.ArrayList;

import java.util.List;

public class Player {

    private boolean isBlue;
    private Board board;
    private List<CheckerPiece> playerPieces;
    private String PlayerName;


    Player(Board theBoard, Boolean isBlue) {
        updateBoard(theBoard);
        setBlue(isBlue);
        if (isBlue) setPlayerName("Player 1");
        else {
            setPlayerName("Player 2");
        }
    }

    Player(Board theBoard, Boolean isBlue, String theName) {
        this(theBoard, isBlue);
        setPlayerName(theName);
    }

    public void setPlayerName(String playerName) {
        this.PlayerName = playerName;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public List<CheckerPiece> getPlayerPieces() {
        return playerPieces;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    //updateBoard run set
    public void updateBoard(Board board) {
        this.board = board;
        setPlayerPieces();
    }

    //method to fill list of pieces player has left
    private void setPlayerPieces() {
        this.playerPieces = new ArrayList<>();
        for (CheckerPiece row[] : this.board.getCheckersArray()) {
            for (CheckerPiece piece :
                    row) {
                if (piece != null) {
                    if (isBlue == true && piece.getIsBlack() == true) playerPieces.add(piece);
                    if (isBlue == false && piece.getIsBlack() == false) playerPieces.add(piece);
                }
            }
        }
    }

    public boolean thisPlayerLost() {
        boolean playerLost = false;
        //lose conditions are if player doesnt have pieces left to move or cannot move a piece because blocked
        if (playerPieces.isEmpty()) playerLost = true;
        else {
            boolean aPieceCanMove = false;
            //iterate over player pieces checking if valid moves available for any of them
            for (CheckerPiece piece :
                    playerPieces) {
                if (!board.checkValidMove(piece).isEmpty()) {
                    aPieceCanMove = true;
                    break;
                }

            }
            //if iterated thru entire list and cannot move any piece player lost
            if (!aPieceCanMove) playerLost = true;
        }


        return playerLost;
    }

    public String toString() {
        String name;
        if (isBlue) {
            name = "\u001B[36m" + this.PlayerName + "\u001B[0m";
        } else {
            name = "\u001B[91m" + this.PlayerName + "\u001B[0m";
        }
        return name;
    }


}