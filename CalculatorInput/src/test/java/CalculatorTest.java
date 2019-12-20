import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("CALCULATOR EPIC")
@Feature("CHECK METHODS")
@Owner("Andersenlab")
@Link(name = "Andersenlab", url = "https://www.andersenlab.com/")
class CalculatorTest extends Actions {

    @Story("Валидация строки")
    @Description("Проверка метода validateString: подается тестовая строка, обработанная строка возвращается на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"'2.0+2'", "-10+2", "-7-3", "0.25+1", "'0.75+100987'", "'075+100987'", "-7/0"})
    void correctStringValidatePositiveTest(String data) {
        Actions actions = new Actions();
        String actualData = actions.validateStringNotValidReturn(data);
        Assertions.assertEquals(data, actualData);
    }

    @Story("Валидация арифметического оператора")
    @Description("Проверка метода FindOperator: в метод подается тестовая строка, определяется знак арифметической операции, возвращается на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"'2.0+2'", "-10*2", "-7-3", "0.25/1", "'0.75+100987'", "'075+100987'"})
    void checkFindOperatorTest(String data) {
        Actions actions = new Actions();
        Operator operator = actions.findOperator(data);
        assertTrue(
                operator.getSearchChar() == '+' ||
                        operator.getSearchChar() == '-' ||
                        operator.getSearchChar() == '*' ||
                        operator.getSearchChar() == '/');
    }

    @Story("Валидация операндов")
    @Description("Проверка метода findNumbers: в метод подается тестовая строка, определяются два операнда, возвращаются на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void findNumbersMustBeSetTwoParametersWithPlusTest() {
        Actions actions = new Actions();
        actions.findNumbers("234+432", 0, '+');
        assertEquals(234, actions.getFirstValue());
        assertEquals(432, actions.getSecondValue());
    }

    @Story("Валидация операндов первый операнд - отрицательный")
    @Description("Проверка метода findNumbers: в метод подается тестовая строка, определяются два операнда, возвращаются на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void findNumbersMustBeSetTwoParametersWithMinusTest() {
        Actions actions = new Actions();
        actions.findNumbers("-234-432", 4, '-');
        assertEquals(actions.getFirstValue(), -234);
        assertEquals(432, actions.getSecondValue());
    }

    @Story("Проверка метода на невалидное значение позиции знака")
    @Description("Проверка метода findNumbers: в метод подается тестовая строка, невалидное значение позиции знака арифметической операции и сам знак, возвращаются на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void findNumbersMustBeErrorTest() {
        Actions actions = new Actions();
        try {
            actions.findNumbers("-234-432", -1, '-');
        } catch (Exception e) {
            return;
        }
        assertTrue(false);
    }

    @Story("Валидация операндов")
    @Description("Проверка метода findNumbers: в метод подается тестовая строка, определяются два операнда, возвращаются на сравнение в тест")
    @Severity(SeverityLevel.CRITICAL)
    @ParameterizedTest
    @CsvSource({"'2.0+2',0,'+'", "'-2.0-2',4,'+'", "'-10*2',0,'*'"})
    void checkFindNumbersTest(String stringToValue, int minusPosition, char searchChar) {
        int korrectorZnaka = 1;

        Actions actions = new Actions();
        actions.findNumbers(stringToValue, minusPosition, searchChar);
        if (stringToValue.substring(0, 1).equals("-")) {
            stringToValue = stringToValue.substring(1);
            korrectorZnaka = -1;
        }
        Pattern pattern = Pattern.compile("[-+*\\/]");
        String[] words = pattern.split(stringToValue);

        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                Assertions.assertEquals((korrectorZnaka * (Double.parseDouble((words[i])))), actions.getFirstValue());
            } else {

                Assertions.assertEquals(Double.parseDouble(words[i]), actions.getSecondValue());
            }
        }
    }
}