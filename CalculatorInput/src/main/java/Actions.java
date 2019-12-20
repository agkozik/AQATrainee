import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Actions {
    private String stringToValue = null;
    private boolean validOperator = true;
    private boolean inProgress = true;
    private double firstValue;
    private double secondValue;

    double getFirstValue() {
        return firstValue;
    }

    double getSecondValue() {
        return secondValue;
    }

    boolean isInProgress() {
        return inProgress;
    }

    String initString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println((char) 27 + "[32m" + "Введите строку для вычисления: " + (char) 27 + "[0m");
        stringToValue = scanner.nextLine();
        return stringToValue;
    }

    String validateString(String stringToValue) {
        int attemptCounter = 3;

        while (validOperator) {
            stringToValue = stringToValue.trim().replace(',', '.');
            Pattern p = Pattern.compile("(-?\\d+(?:\\.\\d+)?)\\s*([-+*\\/])\\s*(-?\\d+(?:\\.\\d+)?)");
            Matcher m = p.matcher(stringToValue);
            validOperator = m.matches();

            if (validOperator) {
                break;
            } else {
                System.out.println("Неправильный ввод. Допустимы Десятичные цифры и арифметические операторы: (+,-,*,/)");
                attemptCounter--;
                if (attemptCounter > 0) {
                    validOperator = true;
                    System.out.println("Оставшееся кол-во попыток: " + attemptCounter);
                    initString();
                    stringToValue = this.stringToValue;
                } else {
                    System.out.println((char) 27 + "[31m" + "Оставшееся кол-во попыток: " + attemptCounter + " Выход из программы" + (char) 27 + "[0m");
                    validOperator = false;

                    System.exit(0);
                }
            }
        }
        return stringToValue;
    }

    String validateStringNotValidReturn(String stringToValue) {
        stringToValue = stringToValue.trim().replace(',', '.');
        Pattern p = Pattern.compile("(-?\\d+(?:\\.\\d+)?)\\s*([-+*\\/])\\s*(-?\\d+(?:\\.\\d+)?)");
        Matcher m = p.matcher(stringToValue);
        validOperator = m.matches();

        if (validOperator) {
            return stringToValue;
        } else {
            System.out.println("Неправильный ввод. Допустимы Десятичные цифры и арифметические операторы: (+,-,*,/)");
            validOperator = false;
            return String.valueOf(validOperator);
        }
    }

    Operator findOperator(String stringToValue) {
        int minusPosition = 0;
        char searchChar = '0';

        for (int i = 1; i < stringToValue.length(); i++) {
            if (stringToValue.charAt(i) == '+') {
                searchChar = '+';
                break;
            } else if (stringToValue.charAt(i) == '-') {
                searchChar = '-';
                minusPosition = i;
                break;
            } else if (stringToValue.charAt(i) == '*') {
                searchChar = '*';
                break;
            } else if (stringToValue.charAt(i) == '/') {
                searchChar = '/';
                break;
            }
        }
        return new Operator(minusPosition, searchChar);
    }

    void findNumbers(String stringToValue, int minusPosition, char searchChar) {
        if (minusPosition > 0) {
            firstValue = Double.parseDouble(stringToValue.substring(0, minusPosition));
            secondValue = Double.parseDouble(stringToValue.substring(minusPosition + 1));
        } else {
            firstValue = Double.parseDouble(stringToValue.substring(0, stringToValue.indexOf(searchChar)));
            secondValue = Double.parseDouble(stringToValue.substring(stringToValue.indexOf(searchChar) + 1));
        }
    }

    void expressionEvaluation(Double firstValue, Double secondValue, char searchChar) {
        inProgress = true;

        switch (searchChar) {
            case '+':
                System.out.println("Результат сложения:"
                        + firstValue + " и " + secondValue + " равен " + (firstValue + secondValue));
                this.inProgress = false;
                break;
            case '-':
                System.out.println("Результат вычитания:"
                        + firstValue + " и " + secondValue + " равен " + (firstValue - secondValue));
                this.inProgress = false;
                break;
            case '*':
                System.out.println("Результат умножения:"
                        + firstValue + " на " + secondValue + " равен " + (firstValue * secondValue));
                this.inProgress = false;
                break;
            case '/':
                try {
                    if (secondValue == 0) {
                        throw new Exception("Деление на ноль невозможно!");
                    } else {
                        System.out.println("Результат деления:"
                                + firstValue + " на " + secondValue + " равен " + (firstValue / secondValue));
                        this.inProgress = false;
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}