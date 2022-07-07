import java.util.Random;
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

}
