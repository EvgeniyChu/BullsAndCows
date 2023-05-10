package version2;

import java.io.*;
import java.time.LocalDate;

public class FileWorker {

    //TODO stroka - это плохо, лучше line или что-нибудь подобное
    public static void writeNameGame (int x, String stroka)  {
        String myFile = "file.txt";
        File file = new File(myFile);

        try (FileWriter writer  = new FileWriter(myFile, true);){
            writer.write("Game №" + x + " " + LocalDate.now() + " Загаданная строка " + stroka);
            writer.append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeRequest (String stroka, int x, String a, int z, String b)  {
        String myFile = "file.txt";
        File file = new File(myFile);

        try (FileWriter writer  = new FileWriter(myFile, true);){
            writer.write("\tЗапрос: " + stroka + "\tОтвет: " + x + a + z + b);
            writer.append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeAmountTries ( int x)  {
        String myFile = "file.txt";
        File file = new File(myFile);

        try (FileWriter writer  = new FileWriter(myFile, true);){
            writer.write("\tСтрока была угадана за " + x + " попыток.");
            writer.append('\n');

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //TODO у тебя метод называется существует ли файл, а он тебе возвращает номер последней игры
    public static int ifFileExists () {
        String myFile = "file.txt";
        File file = new File(myFile);
        int x = 1;
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains("Game")) {
                        x++;
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } return x;

    }

}
