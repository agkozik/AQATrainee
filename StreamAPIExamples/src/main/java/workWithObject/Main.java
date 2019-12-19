package workWithObject;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmai> myList = new ArrayList<>();
        myList.add(new NamePhoneEmai("First", "1 0 0 0 ", "firstEmail@mail.com"));
        myList.add(new NamePhoneEmai("Second", "2 0 0 0 ", "secondEmail@mail.com"));
        myList.add(new NamePhoneEmai("Third", "3 0 0 0 ", "thirdEmail@mail.com"));
        myList.add(new NamePhoneEmai("Fourth", "4 0 0 0 ", "fourthEmail@mail.com"));
        myList.add(new NamePhoneEmai("Third", "3 0 0 0 ", "thirdEmail@mail.com"));

        myList.stream().forEach((s) -> System.out.println(s.name + " " + s.phoneNum + " " + s.email));

        //отобразить (замапить) на новый поток данных
        //только имена и номера телефонов
        Stream<NamePhone> shortList = myList
                .stream()
                .map((item->new NamePhone(item.name,item.phoneNum)));
        System.out.println("Подрезанный список: ");
        shortList.forEach(item->System.out.println(item.name + " "+item.phoneNum));

        // в следующем фрагменте кода для получения нового потока данных, содержащего только
        //элементы имени и номера телефона, совпадающие с именем "Джеймс' сначала вызывается метод fi1ter (),
        // а затем метод map ():
        Stream<NamePhone> filteredList = myList.stream().filter(item-> item.name.equals("Third"))
                .map(item->new NamePhone(item.name,item.phoneNum));
        System.out.println("--------------------------------------------------");
        filteredList.forEach(i-> System.out.println(i.name+" "+i.phoneNum));

//        //Достаем стринг из номера телефона преобразуем в int и складываем
//        IntStream intStream = myList
//                .stream()
//                .mapToInt((i)-> Integer.parseInt(i.phoneNum));
//        OptionalInt numbers = intStream.reduce((a, b)->a+b);
//        numbers.ifPresent(System.out::println);

        //Достаем стринг из номера телефона, подрезаем пробелы,
        // преобразуем в double и складываем значения
        DoubleStream doubleStream = myList
                .stream().map(s-> s.phoneNum.replaceAll(" ",""))
                .mapToDouble(Double::parseDouble);

        OptionalDouble douleNumbers = doubleStream.reduce((a, b)->a+b);
        douleNumbers.ifPresent(System.out::println);
    }
}