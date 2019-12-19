package CollectorsToList;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import workWithObject.NamePhone;
import workWithObject.NamePhoneEmai;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectToList_Set_Linked {
    public static void main(String[] args) {

        ArrayList<HandBook> myArrayList = new ArrayList<>();
        myArrayList.add(new HandBook("First", "1 0 0 0 ", "firstEmail@mail.com"));
        myArrayList.add(new HandBook("Second", "2 0 0 0 ", "secondEmail@mail.com"));
        myArrayList.add(new HandBook("Third", "3 0 0 0 ", "thirdEmail@mail.com"));
        myArrayList.add(new HandBook("Fourth", "4 0 0 0 ", "fourthEmail@mail.com"));
        myArrayList.add(new HandBook("Third", "3 0 0 0 ", "thirdEmail@mail.com"));

        List<Phonebook> phonebooks =
                myArrayList
                        .stream()
                        .map((a) -> new Phonebook(a.name, a.phoneNum))
                        .collect(Collectors.toList());
        for (Phonebook i : phonebooks) {
            System.out.println(i.name + " " + i.phoneNum);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Set:");
        Set<Phonebook> phonebooksSet =
                myArrayList
                        .stream()
                        .map((a) -> new Phonebook(a.name, a.phoneNum))
                        .collect(Collectors.toSet());
        for (Phonebook i : phonebooksSet) {
            System.out.println(i.name + " " + i.phoneNum);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("HashSet:");
        Stream<Phonebook> nameAndPhoneHS =
                myArrayList
                        .stream()
                        .map((a) -> new Phonebook(a.name, a.phoneNum));

        HashSet<Phonebook> npList1 = nameAndPhoneHS.collect(
                HashSet::new,
                HashSet::add,
                HashSet::addAll);

        for (Phonebook i : npList1) {
            System.out.println(i.name + " " + i.phoneNum);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Linked:");
        Stream<Phonebook> nameAndPhone =
                myArrayList
                        .stream()
                        .map((a) -> new Phonebook(a.name, a.phoneNum));

        LinkedList<Phonebook> npList = nameAndPhone.collect(
                LinkedList::new,
                LinkedList::add,
                LinkedList::addAll);

        for (Phonebook i : npList) {
            System.out.println(i.name + " " + i.phoneNum);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Iterator:");
        Stream<HandBook> handBookStream = myArrayList.stream();
        Iterator<HandBook> iterator = handBookStream.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().email);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("SplitIterator:");
        Stream<HandBook> handBookStream2 = myArrayList.stream();
        Spliterator<HandBook> splitIter = handBookStream2.spliterator();
        System.out.println("-----------------------tryAdvance------------------------------");
//tryAdvance ()  возвращает логическое значение
//false, если больше не остается элементов для обработки,
        while (splitIter.tryAdvance(x -> System.out.println(x.name))) ;
        System.out.println("-----------------------------------------------------");
//forEachRemaining выполняет заданное действие над каждым необработанным эле­
//ментом и затем производит возврат.
        splitIter.forEachRemaining((n) -> System.out.println(n.name));

        // trySplit()Этот метод разделяет итерируемые элементы на две части
        // ( может иметь большое значение при параллельной обработке крупных массивов данных)
        System.out.println("-----------------------------------------------------");
        //Создать Stream
        Stream<HandBook> handBookStream3 = myArrayList.stream();
        //получить итератор-разделитель
        Spliterator<HandBook> splitItr = handBookStream3.spliterator();
        //разделить первый итератор
        Spliterator<HandBook> splititr2 = splitItr.trySplit();
        if (splititr2 != null) {
            System.out.println("Результат, выводимый итератором splitItr2: ");
            splititr2.forEachRemaining((n) -> System.out.println(n.name));
        }
        //а  теперь воспользоваться итератором splititr
        System.out.println("\nРезультат, выводимый итератором splitItr: ");
        splitItr.forEachRemaining((n) -> System.out.println(n.name));

        System.out.println("-----------------------------------------------------");
    }
}