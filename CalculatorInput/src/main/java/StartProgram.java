//1. Ввести с консоли строку в формате "2+2" (вместо '+', может быть +-*/).
////2. Обработать введенную строку
////3. Результат должен получится число.
//--------------------------------------------------------
//1. Нет выхода из программы (только по exception), лучше сделать выход из программы по результату успешного вычисления.
//2. При исключении "Некорректный арифметический оператор!" не завершать работу программы.
//3. s+s = NumberFormatException
//4. 1+ = NumberFormatException: empty String
//5. 2++2 = не корректная строка, фактический результат = 4.0
//6. Вывести в разные методы поиск числовых значений и математических операторов, вычисление (как для инициализации строки).

//Исправить (калькулятор):
//1. Метод initString() должен только обрабатывать строку, ввод строки (сканер) с main
//2. Комментариев в коде не должно быть '//minusPosition = 0;'
//3. Ограничить число не удачных потыток ввода до 3 (если 3 раза было введено не валидную строку, завершать работу программы).
//
//Задание:
//1. Покрыть юнит тестами первое задание (с использованием JUnit).

public class StartProgram {
    private static boolean inProgress = true;

    public static void main(String[] args) {

        Actions actions = new Actions();
        while (inProgress) {
            String s = actions.validateString(actions.initString());

            Operator result = actions.findOperator(s);
            actions.findNumbers(s, result.getMinusPosition(), result.getSearchChar());
            actions.expressionEvaluation(actions.getFirstValue(), actions.getSecondValue(), result.getSearchChar());
            inProgress = actions.isInProgress();
        }
    }
}







