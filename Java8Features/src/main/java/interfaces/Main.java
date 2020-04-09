package interfaces;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();

        Dog dog = new Dog();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();
        Dog dog4 = new Dog();
        Dog dog5 = new Dog();

        ArrayList<CanRun> animalArrayList = new ArrayList<>();
        animalArrayList.add(dog1);
        animalArrayList.add(dog2);

        animalArrayList.add(cat1);
        animalArrayList.add(cat2);


        for (CanRun a:animalArrayList) {
            a.run();
        }

        Animal cat777 = new Cat();
        CanRun canRun = (CanRun) cat777;
        ((CanRun) cat777).run();

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(dog);

        for (Animal animal:animals){
            animal.eat();
        }

        Animal animal = new Cat();
        Dog dog777 = new Dog();
        dog777.run();
        Animal dogAnimal = dog777;
        ((Dog) dogAnimal).run();
    }
}