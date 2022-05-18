package com.games.checkers;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Board{
    private String name;
    private String color;
    private int playerNum;
    boolean isBlack;



    public Player(String name) {
        setName(name);
    }

    public Player(String name, String color) {
        this(name);
        setColor(color);
    }

    public Player(String name, String color, boolean isBlack) {
        this(name, color);
        this.isBlack = isBlack;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    

    @Override
    public String toString() {
        return getClass().getName() + ": name=" + getName() + ", color=" + getColor();
    }








}