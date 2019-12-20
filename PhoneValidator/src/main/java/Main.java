
//Валидатор номера телефона:
//1. Ввести строку в формате - +380931235698. Номер может быть валидный в двух случаях (+380... и 09...).
//2. Определить валидный ли номер.
//3. Посчитать сумму цифр для номера телефона, пока не останется она цифра.
//4. Вывести строковое значение результата.
//
//Пример: input=+380931235698, firstLoop = 57, secondLoop = 5 + 7 = 12, thirdLoop = 3, result = Три.

public class Main {
    /**
     * Класс для запуска кода
     */
    public static void main(String[] args) {

        PhoneNumber phoneNumber = new PhoneNumber();
        String number = phoneNumber.initPhoneNumber();
        phoneNumber.calculateSum(number);
    }

}
