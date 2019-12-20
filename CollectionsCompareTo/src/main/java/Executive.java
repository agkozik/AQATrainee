import java.util.HashSet;
import java.util.Set;

public class Executive {
    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(3);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);

        Set<Integer> numbers2= new HashSet<>();
        numbers2.add(2);
        numbers2.add(4);
        numbers2.add(6);
        numbers2.add(8);
        numbers2.add(10);

        System.out.println("Множество А: ");
        for (Integer n:numbers) {
            System.out.print(n+"|");
        }
        System.out.println("\n");
        System.out.println("Множество В: ");

        for (Integer n2:numbers2) {
            System.out.print(n2+"|");
        }
        System.out.println("\n");

        //System.out.println(numbers.equals(numbers2)+" - Два множества А и В равны (А=В), если они состоят из одних и тех же элементов");

        Plurality plurality = new Plurality();
       System.out.println(plurality.union(numbers,numbers2)+" Объедине́ние мно́жеств (тж. су́мма или соедине́ние) — множество, содержащее в себе все элементы исходных множеств.");

       System.out.println(plurality.subtract(numbers,numbers2)+" Разность двух множеств — операция, результатом которой является множество, в которое входят все элементы первого множества, не входящие во второе множество");

       System.out.println(plurality.intersect(numbers,numbers2)+" Пересечением (произведением) множеств А и В называется множество А ∩ В, элементы которого принадлежат как множеству А, так и множеству В");

        System.out.println(plurality.symmetricSubtract(numbers,numbers2)+" Симметрическая разность двух множеств — операция, результатом которой является новое множество, включающее все элементы исходных множеств, не принадлежащие одновременно обоим исходным множествам.");
    }
}