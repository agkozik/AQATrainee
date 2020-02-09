import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Trying {
    public static void main(String[] args) {
        System.out.println("\n------------Задать 10 случайных целых чисел---------------");

        new Random()//генерация случайного значения
                .ints(0,11)//значение типа Int в диапазоне
                .limit(10)//оставить в потоке только 10 значений
                .sorted()//отсортировать используя Comporator
                .forEach(System.out::print);//вывести значения используя ссылку на статический метод
        System.out.println("\n------------Подсчет нулевых строк---------------");
        List<String> list = Arrays.asList("a","","b","","c","d");

        long count = list
                .stream()
                //.parallelStream()
                .filter(tmp->tmp.isEmpty()).count();
        System.out.println(count);

        System.out.println("\n------------Поиск Максимума---------------");
        List<Integer> listMax = Arrays.asList(1,2,3,8,5);
        IntSummaryStatistics max = listMax
                .stream()
                .mapToInt((x)->x).summaryStatistics();
        System.out.println(max.getMax());

        System.out.println("\n------------Использование LocalDate---------------");
        LocalDate todayNow = LocalDate.now();
        LocalDate nextWeek = todayNow.plus(1, ChronoUnit.WEEKS); //добавить неделю
        System.out.println(nextWeek);

        //Получение текущей даты и времени
        Date currentDate = new Date();
        Instant now =currentDate.toInstant();
        ZoneId currentZone = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(now,currentZone);
        System.out.println(localDateTime);

    }
}