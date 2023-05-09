import java.time.LocalDate;
import java.util.Scanner;

public class Game {
    public static void start() {
        GameLogic gl = new GameLogic();
        FileWorker fw = new FileWorker();
        WorkConsole wc = new WorkConsole();
        GenerateNumber gen = new GenerateNumber();
        LocalDate date = LocalDate.now();
        Scanner sc = new Scanner(System.in);
        int countGames = fw.ifFileExists();
        wc.printGreetings();
        gen.pcNum = "";
        gen.generateNumber();
        int bulls = 0;
        int cows = 0;
        int tries = 1;
        boolean notFourBulls = true;
        String cow = "";
        String bull = "";

        fw.writeNameGame(countGames, date, gen.pcNum);

        while (notFourBulls) {
            wc.printGuessNumber(tries);
            String myNum = sc.nextLine();
            bulls = gl.calculateBulls(myNum, gen.pcNum);
            cows = gl.calculateCows(myNum, gen.pcNum);
            cow = gl.checkEndingCows(cows);
            bull = gl.checkEndingBulls(bulls);

            if (bulls == 4) {
                wc.printRequest(myNum, cow, bull, cows, bulls);
                wc.printAmountTries(tries);
                fw.writeRequest(myNum, cows, cow, bulls, bull);
                notFourBulls = false;
            } else {
                wc.printRequest(myNum, cow, bull, cows, bulls);
                fw.writeRequest(myNum, cows, cow, bulls, bull);
                tries++;
            }
        }
        fw.writeAmountTries(tries);
    }
}
