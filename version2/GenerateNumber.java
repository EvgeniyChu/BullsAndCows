import java.util.Random;

public class GenerateNumber {

    public static String pcNum;

    public static String generateNumber() {
        Random rand = new Random();
        String str = "";
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = rand.nextInt(10);
            str += digits[i];
        }
        pcNum = str;
        //Жестко ))
        while (checkDigits(pcNum)) {
            generateNumber();
        }
        return pcNum;
    }

    public static boolean checkDigits(String n) {
        for (int i = 0; i < n.length() - 1; i++) {
            for (int j = i + 1; j < n.length(); j++) {
                if (n.charAt(i) == n.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
