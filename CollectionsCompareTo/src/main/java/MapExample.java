import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();

        map.put(37, "BY");
        map.put(45, "UA");
        map.put(2, "PL");

//        System.out.println(map.get(1));
     //   System.out.println(map.keySet());
     //  System.out.println(map.values());
     //   System.out.println(map.size());
//        System.out.println(map.containsKey(2));
//        System.out.println(map.containsValue("BY"));
       System.out.println(map.replace(37,"USA"));
//        System.out.println(map.get(3));
//        System.out.println(map.entrySet());
//        System.out.println(map.put(3,"PL"));
      System.out.println(map.entrySet());
//
//        for (Map.Entry<Integer, String> i:map.entrySet()) {
//            System.out.println("Key ="+i.getKey()+" Value ="+i.getValue());
//        }

    }
}