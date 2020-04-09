import java.util.ArrayList;
import java.util.TreeSet;

public class CollectionsArraySet {
    public static void main(String[] args) {
        TreeSet<Integer> setInt = new TreeSet<>();
        setInt.add(5);
        setInt.add(2);
        setInt.add(7);
        setInt.add(7);
        setInt.add(8);
        setInt.add(4);
        setInt.add(10);
        for (Integer i:setInt) {
            System.out.print(i+" ");
        }
        System.out.println("");

        ArrayList<Integer> figures = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        figures.add(1);
        figures.add(2);
        figures.add(3);
        figures.add(4);
        figures.add(5);
        figures.add(6);
        figures.add(7);
        figures.add(8);
        figures.add(9);
        figures.add(10);

        names.add("01");
        names.add("02");
        names.add("03");
        names.add("04");
        names.add("05");
        names.add("06");
        names.add("07");
        names.add("08");
        names.add("09");
        names.add("010");
        ArrayList<String> common = new ArrayList<>();
        for (int i = 0; i <names.size(); i++) {
            common.add(figures.get(i)+"-"+names.get(i));
        }
        for (String c:common) {
            System.out.println(c);
        }
    }
}