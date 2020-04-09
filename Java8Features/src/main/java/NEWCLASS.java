import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NEWCLASS {
    public static void main(String[] args) {
        System.out.println("--------------------Replace---------------------------");
        String sStr = "kgsljkghassjg;fsdSSkgklgS";
        String newStr = sStr
                .trim()
                .replaceAll("s", "")
                .replaceAll("S", "");
        System.out.println(newStr);

        System.out.println("--------------------StreamAPI-------------------------");

        sStr
                .chars()
                .mapToObj(x -> (char) x)
                .filter(x -> !x.equals('s')&&!x.equals('S'))
                .forEach(System.out::print);
        System.out.println("\n--------------------StreamAPI-------------------------");

        char[] chars = sStr.toCharArray();
        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i]!='s'&&chars[i]!='S'){
                characterList.add(chars[i]);
            }
        }

        for (Character i:characterList) {
            System.out.print(i);
        }

//        LinkedList<String> linkedList = new LinkedList<>();
//        linkedList.add("1");
//        linkedList.add("2");
//        //linkedList.addFirst("3");
//        linkedList.add("4");
//        linkedList.addLast("5");
//        linkedList.add("6");
//
//        for (String i:linkedList) {
//            System.out.println(i);
//        }
//
//        Iterator<String> iter = linkedList.iterator();
//        while (iter.hasNext()){
//            System.out.println(iter.next());
//        }
//
//        ArrayList<Integer> arrayList =new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(3);
//        arrayList.add(4);
//
//        Iterator<Integer> iterator = arrayList.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//
//        Set<Integer> set = new HashSet<>();
//        set.addAll(arrayList);
//
//        for (Integer i:set) {
//            System.out.println(i);
//        }
//
//        arrayList.get(0);
//        linkedList.get(0);
        System.out.println("\n--------------------Factorial-------------------------");
        System.out.println(factorial(4));
        System.out.println("\n--------------------FactorialStream-------------------------");
        System.out.println(factorialStream(4));
        System.out.println("\n--------------------FactorialRecursion-------------------------");
        System.out.println(factorialRecursion(4));
    }

    static int factorial(int n){
        int factorial = 1;
        for (int i = 2; i <=n; i ++){
            factorial*= i;
        }
        return factorial;
    }

    static int factorialStream(int n){
        int factorial = 1;
        return IntStream
                .rangeClosed(2, n)
                .reduce(1, (a, b) -> a * b);
    }
    static int factorialRecursion(int n){
            if (n > 20) throw new IllegalArgumentException(n + " is out of range");
            return (1 > n) ? 1 : n * factorial(n - 1);
    }
}