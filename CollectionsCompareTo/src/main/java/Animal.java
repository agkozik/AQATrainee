import java.util.*;

public class Animal implements Comparable<Animal>{
    private String name;
    private Integer strength;

    private Animal(String name, Integer strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Cat",3));
        animals.add(new Animal("dog",2));
        animals.add(new Animal("elephant",45));
//
//        for(Animal animal:animals){
//            System.out.println(animal.getStrength());
//        }
//        Iterator<Animal> iter = animals.iterator();
//
//        while(iter.hasNext()){
//            System.out.println(iter.next());
//
//        }
        ListIterator<Animal> listIterator=animals.listIterator();
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());

            listIterator.remove();
        }

        listIterator.set(animals.get(2));
        while(listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }

        System.out.println("________________________________");
        //Отсортировать лист Animal по полю strength
        Collections.sort(animals, new Comparator<Animal>() {
            public int compare(Animal a1, Animal a2) {
                return a1.getStrength().compareTo(a2.getStrength());
            }
        });
        //вывод результатов
        for(Animal animal:animals){
            System.out.println(animal.toString());
        }
    }

    public int compareTo(Animal anotherAnimal) {
        return strength.compareTo(anotherAnimal.getStrength());
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                '}';
    }
}