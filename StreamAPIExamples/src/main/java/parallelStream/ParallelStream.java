package parallelStream;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class ParallelStream {
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

        //Перемножение элементов списка в параллельном потоке (1й способ)
        Optional<Integer> prodObj =
                myList
                        .parallelStream()
                        .reduce((a, b) -> a * b);
        System.out.println(prodObj.get());

        //Перемножение элементов списка в параллельном потоке (2й способ)
        int parallelMultiplyNumbers=myList
                .parallelStream()
                .reduce(1,(a,b)->a*b,(a,b)->a*b);
        System.out.println(parallelMultiplyNumbers);
    }
}