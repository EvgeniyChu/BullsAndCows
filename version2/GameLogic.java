import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameLogic {

    public static int calculateBulls(String n1, String n2) {
        int count = 0;

        for (int i = 0; i < n1.length(); i++) {
            if (n1.charAt(i) == n2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static int calculateCows(String n1, String n2) {
        int count = 0;
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                if (i != j) {
                    if (n1.charAt(i) == n2.charAt(j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static String checkEndingCows(int n) {
        String cow = "";
        if (n == 0) {
            cow = " коров ";
        } else if (n == 1) {
            cow = " корова ";
        } else cow = " коровы ";
        return cow;
    }

    public static String checkEndingBulls(int n) {
        String bull = "";
        if (n == 0) {
            bull = " быков ";
        } else if (n == 1) {
            bull = " бык ";
        } else bull = " быка ";
        return bull;
    }
}
