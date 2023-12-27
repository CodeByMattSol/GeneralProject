package org.main;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    public boolean makeTurn(Board board) {
        Scanner scanner = new Scanner(System.in);

        int row;
        int col;

        System.out.println("Ходит игрок "+ this.getName() + " (" + this.getSymbol() + ").");

        do {
            System.out.print("Введите координаты хода в формате \"строка столбец\": ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt() - 1;

                if (row == -1) {
                    return false;
                }
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt() - 1;
                } else {
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 15.");
                    continue;
                }
            } else {
                scanner.nextLine();
                System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 15.");
                continue;
            }
            if (!board.makeTurn(row,col,this.getSymbol())) {
                System.out.println("Клетка с такими координатами занята или не существует.");
                System.out.println("Попробуйте ещё раз ... ");
                continue;
            }
            return true;
        } while (true);
    }
}
