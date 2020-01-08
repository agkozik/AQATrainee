import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(37, "BY");
        map.put(45, "UA");
        map.put(2, "PL");
        System.out.println("get(1) "+map.get(1));//null
        System.out.println("get(37) "+map.get(37));//BY
        //System.out.println(map.keySet());
        //  System.out.println(map.values());
        //   System.out.println(map.size());
       System.out.println("containsKey(2) "+map.containsKey(2));//true
//        System.out.println(map.containsValue("BY"));
        System.out.println(map.replace(37, "USA"));
//        System.out.println(map.get(3));
//        System.out.println(map.entrySet());
//        System.out.println(map.put(3,"PL"));
        System.out.println("Вывод EntySet "+map.entrySet());

        //перебор значений из Map 1й способ
        for (Map.Entry<Integer, String> i : map.entrySet()) {
            System.out.println("Key =" + i.getKey() + " Value =" + i.getValue());
        }
        //перебор значений из Map 2й способ
        Set<Integer> keys = map.keySet();
        Iterator<Integer> iterator = keys.iterator();

        while (iterator.hasNext()) {
            System.out.println(map.get(iterator.next()));
        }

    }
}