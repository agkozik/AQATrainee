package lyambdas;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

import static java.lang.Double.*;

public class LyambdaRunner {

    public static void main(String[] args) {
        FiguresAddition<Double> divide = (a, b) -> (a / b);
        System.out.println(divide.add(2.0, 6.0));

        PredicateFigures<Integer> isEquals = (a, b) -> (a > b);
        System.out.println(isEquals.compare(2, 3));

        PredicateFigures<Double> isEqualsTo = (a, b) -> (a > b);
        System.out.println(isEqualsTo.compare(2.0, 3.1));

        FunctionalRevers<String> reverseStr = (str) -> {
            StringBuilder result = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                result.append(str.charAt(i));
            }
            return result.toString();
        };
        System.out.println(reverseStr.reverse("9876543210"));
        String str="0123456789";

        ArrayList<Character> list = new ArrayList<>();
        for (int i = 1; i <str.length()-1; i++) {
            list.add(str.charAt(i));
        }

//        OptionalDouble avgValue = list
//                .stream()
//                .flatMapToDouble((x)-> DoubleStream.of(parseDouble(String.valueOf(x))))
//                .average();

        list.stream().mapToInt(x -> str.length()).forEach(System.out::println);

//        System.out.println(avgValue.toString());
//        String[] array =someString.t;

//
//        //Arrays.stream
//        Stream<String> stream1 = Arrays.stream(array);
//        stream1.forEach(x -> System.out.println(x));
//
//        //Stream.of
//        Stream<String> stream2 = Stream.of(array);
//        stream2.forEach(x -> System.out.println(x));
    }
}