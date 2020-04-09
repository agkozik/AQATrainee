package baseStream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class BaseStreamApiOperations {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(4);
        myList.add(2);
        myList.add(3);
        myList.add(8);
        myList.add(54);
        myList.add(54);
        myList.add(37);
        System.out.println("Исходный список: " + myList);
        Stream<Integer> myStream = myList.stream();

        //Кол-во элементов в списке
        System.out.println("Кол-во элементов в списке: " + myStream.count());

        //Минимальное значение в списке:
        myStream = myList.stream();
        Optional<Integer> minValue = myStream
                .min(Integer::compare);
        if (minValue.isPresent()) System.out.println("Минимальное значение в списке: "
                + minValue);

        //Максимальное значение в списке:
        myStream = myList.stream();
        Optional<Integer> maxValue = myStream
                .max(Integer::compare);
        if (maxValue.isPresent()) {
            System.out.println("Максимальное значение в списке: " + maxValue);
        }

        //Сортировка списка
        Stream<Integer> sortedStream = myList
                .stream()
                .sorted();
        System.out.print("Отсортированный ПОТОК: ");
        sortedStream.forEach((item) -> System.out.print(item + ", "));
        System.out.println("\nИсходный список:      " + myList);

        //Вывод только нечетных, целочисленных значений, используя filter()
        Stream<Integer> values = myList
                .stream()
                .sorted()
                .filter((n) -> (n % 2) == 1);
        System.out.print("Вывод нечетных значений: ");
        values.forEach((n) -> System.out.print(n + " "));

        //Вывод только тех нечетных значений, которые больше 5
        Stream<Integer> oddVall =
                myList.stream()
                        .sorted()
                        .filter((n) -> (n % 2) == 1)
                        .filter((n) -> (n > 5));
        System.out.print("\nНечетные значения больше 5: ");
        oddVall.forEach(System.out::print);

        //найти число 3 в списке
        myList.stream()
                .filter((n) -> (n == 3))
                .forEach(System.out::println);

        //перемножить все числа в списке (1й способ)
        int multiplyNumbers = myList
                .stream()
                .reduce(1, (a, b) -> {
            if (a != 0 || b != 0) return a * b;
            else return 0;
        });
        System.out.println(multiplyNumbers);

        //сложить все числа в списке (1й способ)
        int addNumbers = myList
                .stream()
                .reduce(0, (a, b) -> {
            if (a != 0 || b != 0) return a + b;
            else return 0;
        });
        System.out.println(addNumbers);

        //перемножить все числа в списке (2й способ)
        Optional<Integer> multiplyObject = myList
                .stream()
                .reduce((a, b) -> a * b);
        multiplyObject.ifPresent(System.out::println);

        //сложить все числа в списке (2й способ)
        Optional<Integer> addObject2 = myList
                .stream()
                .reduce((a, b) -> a + b);
        addObject2.ifPresent(System.out::println);
    }
}