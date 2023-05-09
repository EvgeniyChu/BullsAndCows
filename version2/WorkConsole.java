public class WorkConsole {
    public static void printGreetings() {
        System.out.println("Давайте сыграем в игру 'Быки и коровы'");
        System.out.println("Я загадал 4-х значное число без повторения цифр");
        System.out.println("Вам необходимо ввести 4-х значное число");
        System.out.println("Количество быков = полное совпадение Вашей цифры с цифрой компьютера");
        System.out.println("Количество коров = Ваша цифра присутствует в загаданном числе");
    }
    public static void printRequest(String a, String b, String c, int x, int z) {
        System.out.println("\tЗапрос: " + a + "\tОтвет: " + x + b + z + c);
    }
    public static void printGuessNumber(int x) {
        System.out.print("Введите число " + x + ": ");
    }
    public static void printAmountTries (int x) {
        System.out.println("\tСтрока была угадана за " + x + " попыток.");
    }

    public static void playAgain() {
        System.out.println("Хотите ли вы сыграть снова: 'yes/no'");
    }
    public static void goodbye() {
        System.out.println("До свидания!");
    }
}
