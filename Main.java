import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static void printSomething() {
        System.out.println("Давайте сыграем в игру 'Быки и коровы'");
        System.out.println("Я загадал 4-х значное число без повторения цифр");
        System.out.println("Вам необходимо ввести 4-х значное число");
        System.out.println("Количество быков = полное совпадение Вашей цифры с цифрой компьютера");
        System.out.println("Количество коров = Ваша цифра присутствует в загаданном числе");
    }

    private static String pcNum = "";

    private static String generateNumber() {
        Random rand = new Random();
        String str = "";
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = rand.nextInt(10);
            str += digits[i];
        }
        pcNum = str;
        while (checkDigits(pcNum)) {
            generateNumber();
        }
        return pcNum;
    }

    private static boolean checkDigits(String n) {
        for (int i = 0; i < n.length() - 1; i++) {
            for (int j = i + 1; j < n.length(); j++) {
                if (n.charAt(i) == n.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int calculateBulls(String n1, String n2) {
        int count = 0;

        for (int i = 0; i < n1.length(); i++) {
            if (n1.charAt(i) == n2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private static int calculateCows(String n1, String n2) {
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

    private static String checkEndingCows(int n) {
        String cow = "";
        if (n == 0) {
            cow = " коров ";
        } else if (n == 1) {
            cow = " корова ";
        } else cow = " коровы ";
        return cow;
    }

    private static String checkEndingBulls(int n) {
        String bull = "";
        if (n == 0) {
            bull = " быков ";
        } else if (n == 1) {
            bull = " бык ";
        } else bull = " быка ";
        return bull;
    }

    public static void main(String[] args) {
        String myFile = "file.txt";
        File file = new File(myFile);
        int countGames = 1;
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains("Game")) {
                        countGames++;
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        Scanner sc = new Scanner(System.in);
        printSomething();
        generateNumber();

        int bulls = 0;
        int cows = 0;
        int tries = 1;
        boolean flag = true;
        String cow = "";
        String bull = "";


        try (FileWriter writer = new FileWriter("file.txt", true)) {
            writer.write("Game №" + countGames + " " + formatter.format(date) + " Загаданная строка " + pcNum);
            writer.append('\n');
            while (flag) {
                System.out.print("Введите число " + tries + ": ");
                String myNum = sc.nextLine();
                bulls = calculateBulls(myNum, pcNum);
                cows = calculateCows(myNum, pcNum);
                cow = checkEndingCows(cows);
                bull = checkEndingBulls(bulls);

                if (bulls == 4) {
                    System.out.println("\tЗапрос: " + myNum + "\tОтвет: " + cows + cow + bulls + bull);
                    System.out.println("\tСтрока была угадана за " + tries + " попыток.");
                    writer.write("\tЗапрос: " + myNum + "\tОтвет: " + cows + cow + bulls + bull);
                    writer.append('\n');
                    flag = false;
                } else {
                    System.out.println("\tЗапрос: " + myNum + "\tОтвет: " + cows + cow + bulls + bull);
                    writer.write("\tЗапрос: " + myNum + "\tОтвет: " + cows + cow + bulls + bull);
                    writer.append('\n');
                    tries++;
                }
            }
            writer.write("\tСтрока была угадана за " + tries + " попыток.");
            writer.append('\n');

            writer.flush();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}