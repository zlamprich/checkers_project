package com.games.checkers;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Board {
    public static final int rows = 8;
    public static final int columns = 8;
    public int size;


    public Pieces[][] board;

    public Board () {
        this.board = new Pieces[rows][columns];

    }

    public Pieces[][] getBoard() {
        return board;
    }

    private class Pieces {
    }
}