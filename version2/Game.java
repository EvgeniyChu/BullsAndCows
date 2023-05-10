package version2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {
    public void start() {
        //TODO если у тебя все методы в классах статические, то можно не создавать объекты этих классов
//        GameLogic gl = new GameLogic();
//        FileWorker fw = new FileWorker();
//        WorkConsole wc = new WorkConsole();
//        GenerateNumber gen = new GenerateNumber();
        //TODO создание LocalDate можно перенести прямо в метод writeNameGame
//        LocalDate date = LocalDate.now();
        //TODO создание сканера можно перенести в класс WorkConsole
        Scanner sc = new Scanner(System.in);
        //TODO вычисление последней игры можно выполнять внутри FileWorker
        int countGames = FileWorker.ifFileExists();
        WorkConsole.printGreetings();
        String generatedNumber = GenerateNumber.generateNumber();
        int bulls = 0;
        int cows = 0;
        int tries = 1;
        boolean notFourBulls = true;
        String cow = "";
        String bull = "";

        FileWorker.writeNameGame(countGames, generatedNumber);

        while (notFourBulls) {
            WorkConsole.printGuessNumber(tries);
            String myNum = sc.nextLine();
            bulls = GameLogic.calculateBulls(myNum, generatedNumber);
            cows = GameLogic.calculateCows(myNum, generatedNumber);
            cow = GameLogic.checkEndingCows(cows);
            bull = GameLogic.checkEndingBulls(bulls);

            if (bulls == 4) {
                WorkConsole.printRequest(myNum, cow, bull, cows, bulls);
                WorkConsole.printAmountTries(tries);
                FileWorker.writeRequest(myNum, cows, cow, bulls, bull);
                notFourBulls = false;
            } else {
                WorkConsole.printRequest(myNum, cow, bull, cows, bulls);
                FileWorker.writeRequest(myNum, cows, cow, bulls, bull);
                tries++;
            }
        }
        FileWorker.writeAmountTries(tries);
        playAgain();
    }

    private void playAgain() {
        Scanner sc = new Scanner(System.in);
        Pattern regex=Pattern.compile("^\\d{3}$|^yes$");
        WorkConsole.playAgain();
        String ans = sc.next();
        if (regex.matcher(ans).find()) {start();}
        else WorkConsole.goodbye();
    }
}
