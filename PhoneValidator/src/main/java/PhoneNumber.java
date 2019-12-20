import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс Запрос строки у пользователя, обработка
 */
class PhoneNumber {
    private String inputString;
    private int counter = 0;

    /**
     * Запрос String у пользователя, валидация по шаблону
     *
     * @return String для дальнейшей работы
     */
    String initPhoneNumber() {
        boolean validPhoneNumber = false;
        boolean inProgress = true;

        while (inProgress) {
            Scanner scanner = new Scanner(System.in);
            System.out.println((char) 27 + "[32m" + "Введите телефонный номер в формате без пробелов: [+380xxx xxx xxx] или [0 xxx xxx xxx]" + (char) 27 + "[0m");
            inputString = scanner.nextLine().trim();

            //если допускается ввод с пробелами: удалять все пробелы и невидимые символы (например, tab, \n).
            //inputString = inputString.replaceAll("\\s+", "");
            //экранирую исключения из шаблона без пробелов [+380 xxx xxx xxx] или [0 xxx xxx xxx]
            Pattern pattern = Pattern.compile("((0|\\+380)\\d{9})");
            Matcher matcher = pattern.matcher(inputString);
            validPhoneNumber = matcher.matches();

            if (validPhoneNumber) {
                System.out.println("Номер валидный");
                inProgress = false;
                break;
            } else {
                System.out.println((char) 27 + "[31m" + "Проверьте номер который вы набрали! Введите телефонный номер в формате без пробелов: [+380 xxx xxx xxx] или [0 xxx xxx xxx]" + (char) 27 + "[0m");
            }
        }
        return inputString;
    }

    /**
     * Принимает String, производит вычисление суммы всех чисел и выодит на консоль, пока не останется одноразрядное число.
     */
    void calculateSum(String inputString) {
        int sum = 0;

        String[] numbersToString = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] numberOperation = {"ZeroLoop", "FirstLoop", "SecondLoop", "ThirdLoop", "FourthLoop", "FifthLoop", "sixthLoop", "seventhLoop", "EighthLoop", "NinthLoop"};

        for (int i = 0; i < inputString.length(); i++) {
            char tmp = inputString.charAt(i);
            if (tmp == '+') {
                continue;
            }
            sum = sum + Integer.parseInt(String.valueOf(tmp));
        }
        counter++;

        if (sum > 9) {

            System.out.println(numberOperation[counter] + "=" + sum);

            calculateSum(String.valueOf(sum));
        } else {
            System.out.println(numberOperation[counter] + "=" + sum);

            System.out.println("Result" + "=" + numbersToString[sum]);
        }
    }

}



