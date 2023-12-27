package org.main;

public abstract class Player {
    protected String name;
    protected char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public abstract boolean makeTurn(Board board);

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

