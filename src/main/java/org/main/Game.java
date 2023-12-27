package org.main;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game() {
        board = new Board();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя первого игрока (крестик):");
        String name1 = scanner.nextLine();
        System.out.println("Введите имя второго игрока (нолик):");
        String name2 = scanner.nextLine();

        player1 = new HumanPlayer(name1, 'X');
        player2 = new HumanPlayer(name2, 'O');

        currentPlayer = player1;
    }

    public void start() {
        System.out.println("Добро пожаловать в игру Рэндзю!");
        System.out.println("Цель игры - собрать пять своих символов в ряд по горизонтали, вертикали или диагонали.");
        System.out.println("Игроки ходят по очереди, ставя крестики и нолики на свободные клетки поля.");
        System.out.println("Игра заканчивается, когда один из игроков выигрывает или когда поле заполняется полностью.");
        System.out.println("Если игрок хочет сдаться досрочно, то нужно в качестве первой координаты хода ввести ноль.");
        System.out.println("Желаем вам удачи и приятной игры!");

        play();
    }

    public void play() {
        board.printBoard();

        boolean gameOver = false;
        while (!gameOver) {
            gameOver = isGameOver() || !currentPlayer.makeTurn(this.board);

            if (gameOver) {
                showResult();
            } else {
                switchPlayer();
            }
        }

        System.out.println("Хотите сыграть еще раз? Введите Y, если да, или N, если нет:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("Y")) {
            Game game = new Game();
            game.start();
        } else {
            System.out.println("Спасибо за игру! До свидания!");
            System.exit(0);
        }
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public boolean isGameOver() {
        if (board.isWin(currentPlayer.getSymbol()) || board.isDraw()) {
            return true;
        }

        return false;
    }

    public void showResult() {
        if (board.isWin(currentPlayer.getSymbol())) {
            System.out.println("Поздравляем! " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") выиграл!");
        } else if (board.isDraw()) {
            System.out.println("Ничья! Никто не выиграл!");
        } else {
            System.out.println("Игрок " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") сдался!");
        }
    }

}

