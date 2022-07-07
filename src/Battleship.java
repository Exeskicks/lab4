import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    public Battleship() {}








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

    public void placeships(String playerName, int[][] battlefield) {
        Scanner scanner = new Scanner(System.in);
        int deck = 4;
        while (deck >= 1) {
            System.out.println();
            System.out.println(playerName + ", please place your " + deck + "-deck ship on the battlefield:");
            System.out.println();

            drawField(battlefield);

            System.out.println("Please enter OX coordinate:");
            int x = scanner.nextInt();
            System.out.println("Please enter OY coordinate:");
            int y = scanner.nextInt();
            System.out.println("Choose direction:");
            System.out.println("1. Vertical.");
            System.out.println("2. Horizontal.");
            int direction = scanner.nextInt();
            if (!available(x, y, deck, direction, battlefield)){
                System.out.println("Wrong coordinates!");
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


    public boolean makeTurn(String playerName, int[][] monitor, int[][] battlefield) {
        //Scanner scanner = new Scanner(System.in);
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            while (true) {
                System.out.println(playerName + ", please, make your turn.");
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
                System.out.println("Please enter OX coordinate:");
                int x = 1;
                System.out.println("Please enter OY coordinate:");
                int y = 1;
                if (battlefield[x][y] == 1) {
                    System.out.println("Hit! Make your turn again!");
                    monitor[x][y] = 2;
                } else {
                    System.out.println("Miss! Your opponents turn!");
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
}
