package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class JacksonParserExample {
    private static final String PATH = "C:\\DZ\\AQATrainee\\JsonParsersTrainee\\src\\main\\resources\\";
    private static final String FILETOOBJECT = "json.txt";
    private static final String STRINGTOJSON = "Jackson.json";
    private static ArrayList<Person> people;

    public static void main(String[] args) {
        long ls=System.currentTimeMillis();
        people=parseToObjectUsingJackson();
        addPersons(people);
        rewriteJSONUsingJackson(people);
        long lf=System.currentTimeMillis();
        System.out.println(lf-ls);
    }

    private static void addPersons(ArrayList<Person> people) {
        ArrayList<FriendsItem> frl = new ArrayList();
        List<String> taglist = new ArrayList();
        Person p1,p2,p3,p4;
        p1 = new Person("1", "2", 2411341.2, "efg", "rqef", 124, "wafad", false, "dgsgs", frl, taglist, "fav", "al", "111", "222", "333", "44", "555", "11111", 34, "sag", 15.2);
        p2 = new Person("2", "2", 2411341.2, "efg", "rqef", 124, "wafad", false, "dgsgs", frl, taglist, "fav", "al", "111", "222", "333", "44", "555", "22222", 34, "sag", 15.2);
        p3=people.get(1);
        p4=people.get(2);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
    }

    private static ArrayList<Person> parseToObjectUsingJackson() {
        ArrayList<Person> personList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CollectionType typeReference =
                    TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Person.class);
            personList = objectMapper.readValue(new File(PATH + FILETOOBJECT), typeReference);
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        return personList;
    }

     private static void rewriteJSONUsingJackson(ArrayList<Person> personArrayList) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(personArrayList);
        } catch (JsonProcessingException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        try {
            Files.write(Paths.get(PATH + STRINGTOJSON), jsonString.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
    }
}