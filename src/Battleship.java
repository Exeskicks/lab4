import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    public Battleship() {}


    static String playerName1 = "Player#1";
    static String playerName2 = "Player#2";
    static Scanner scanner = new Scanner(System.in);
    static int[][] battlefield1 = new int[10][10];
    static int[][] battlefield2 = new int[10][10];
    static int[][] monitor1 = new int[10][10];
    static int[][] monitor2 = new int[10][10];

    public static void main(String[] args) {
        System.out.println("Игрок№1, пожалуйста, введите свое имя:");
        playerName1 = scanner.nextLine();
        System.out.println("Игрок№2, пожалуйста, введите свое имя::");
        playerName2 = scanner.nextLine();
        placeships(playerName1, battlefield1);
        placeships(playerName2, battlefield2);
        while (true) {
            makeTurn(playerName1, monitor1, battlefield2);
            if (wincondition()) {
                break;
            }
            makeTurn(playerName2, monitor2, battlefield1);
            if (wincondition()) {
                break;
            }
        }

    }





    public static int drawField(int[][] battlefield) {
        int i = 0;
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (; i < battlefield.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < battlefield[1].length; j++) {
                if (battlefield[j][i] == 0) {
                    System.out.print("- ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        return i;
    }

    public static boolean available(int x, int y, int deck, int rotation, int[][] battlefield) {
        if (rotation == 1) {
            if (y + deck > battlefield.length) {
                return false;
            }
        }
        if (rotation == 2){
            if (x + deck > battlefield[0].length){
                return false;
            }
        }

        while (deck!=0){
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (rotation == 1){
                    yi = i;
                } else{
                    xi = i;
                }

                if (x + 1 + xi < battlefield.length && x + 1 + xi >= 0){
                    if (battlefield[x + 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (x - 1 + xi < battlefield.length && x - 1 + xi >= 0){
                    if (battlefield[x - 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (y + 1 + yi < battlefield.length && y + 1 + yi >= 0){
                    if (battlefield[x + xi][y + 1 + yi]!=0){
                        return false;
                    }
                }
                if (y - 1 + yi < battlefield.length && y - 1 + yi >= 0){
                    if (battlefield[x + xi][y - 1 + yi]!=0){
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }


    public static boolean clearscreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            return true;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void placeships(String playerName, int[][] battlefield) {
        Scanner scanner = new Scanner(System.in);
        int deck = 4;
        while (deck >= 1) {
            System.out.println();
            System.out.println(playerName + ", пожалуйста, разместите свой " + deck + "-палубный корабль на поле боя:");
            System.out.println();

            drawField(battlefield);

            System.out.println("Пожалуйста, введите координаты OX:");
            int x = scanner.nextInt();
            System.out.println("Пожалуйста, введите координаты OY:");
            int y = scanner.nextInt();
            System.out.println("Выберите направление:");
            System.out.println("1. Вертикальный.");
            System.out.println("2. Горизонтальный.");
            int direction = scanner.nextInt();
            if (!available(x, y, deck, direction, battlefield)){
                System.out.println("Неверные координаты!");
                continue;
            }
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    battlefield[x][y + i] = 1;
                } else {
                    battlefield[x + i][y] = 1;
                }
            }
            deck--;
            clearscreen();
        }

    }


    public static boolean makeTurn(String playerName, int[][] monitor, int[][] battlefield) {
        Scanner scanner = new Scanner(System.in);
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            while (true) {
                System.out.println(playerName + ", пожалуйста, сделайте свой ход.");
                System.out.println("  0 1 2 3 4 5 6 7 8 9");
                for (int i = 0; i < monitor.length; i++) {
                    System.out.print(i + " ");
                    for (int j = 0; j < monitor[1].length; j++) {
                        if (monitor[j][i] == 0) {
                            System.out.print("- ");
                        } else if (monitor[j][i] == 1) {
                            System.out.print(". ");
                        } else {
                            System.out.print("X ");
                        }
                    }
                    System.out.println();
                }
                System.out.println("Пожалуйста, введите координаты OX:");
                int x = scanner.nextInt();
                System.out.println("Пожалуйста, введите координаты OY:");
                int y = scanner.nextInt();
                if (battlefield[x][y] == 1) {
                    System.out.println("Попал! Сделай свой ход снова!");
                    monitor[x][y] = 2;
                } else {
                    System.out.println("Промах! Вашь противник ходит!");
                    monitor[x][y] = 1;
                    break;
                }
                clearscreen();
            }
            return true;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean wincondition() {
        int counter1 = 0;
        for (int i = 0; i < monitor1.length; i++) {
            for (int j = 0; j < monitor1[i].length; j++) {
                if (monitor1[i][j] == 2) {
                    counter1++;
                }
            }
        }

        int counter2 = 0;
        for (int i = 0; i < monitor2.length; i++) {
            for (int j = 0; j < monitor2[i].length; j++) {
                if (monitor2[i][j] == 2) {
                    counter2++;
                }
            }
        }

        if (counter1 >= 10) {
            System.out.println(playerName1 + " Победа!!!");
            return true;
        }
        if (counter2 >= 10) {
            System.out.println(playerName2 + " Победа!!!");
            return true;
        }
        return false;
    }

}
