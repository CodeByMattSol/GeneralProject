package org.main;

import java.text.DecimalFormat;

public class Board {
    private char[][] board;
    private final int size;
    private final char empty = '.';
    private final int winLength;
    private int emptyCounter;

    public Board() {
        size = 15;
        winLength = 5;
        emptyCounter = size * size;
        initBoard();
    }

    public Board(int size, int winLength) {
        this.size = size;
        this.winLength = winLength;
        emptyCounter = size * size;
        initBoard();
    }

    public void initBoard() {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = empty;
            }
        }
        emptyCounter = size * size;
    }

    public void printBoard() {
        DecimalFormat dF = new DecimalFormat("00");
        System.out.print("   ");
        for (int j = 0; j < size; j++) {
            System.out.print(dF.format(j+1) + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(dF.format(i+1) + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public boolean isTurnValid(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if (board[row][col] != empty) {
            return false;
        }

        return true;
    }
    public boolean makeTurn(int row, int col, char player) {
        if (isTurnValid(row, col)) {
            board[row][col] = player;
            emptyCounter--;
            printBoard();
            return true;
        }
        return false;
    }

    public boolean isWin(char player) {
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == winLength) {
                    return true;
                }
            }
        }
        for (int j = 0; j < size; j++) {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == winLength) {
                    return true;
                }
            }
        }
        for (int k = 0; k <= size - winLength; k++) {
            int count = 0;
            for (int i = k, j = 0; i < size && j < size; i++, j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == winLength) {
                    return true;
                }
            }

            count = 0;
            for (int i = 0, j = k; i < size && j < size; i++, j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == winLength) {
                    return true;
                }
            }
        }

        for (int k = 0; k <= size - winLength; k++) {
            int count = 0;
            for (int i = k, j = size - 1; i < size && j >= 0; i++, j--) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == winLength) {
                    return true;
                }
            }

            count = 0;
            for (int i = 0, j = size - 1 - k; i < size && j >= 0; i++, j--) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == winLength) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDraw() {
        return emptyCounter == 0;
    }
}
