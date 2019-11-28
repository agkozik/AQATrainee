package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JacksonParserExample {
    private static final String PATH = "C:\\DZ\\AQATrainee\\JsonParsersTrainee\\src\\main\\resources\\";
    private static final String FILETOOBJECT = "json.txt";
    private static final String STRINGTOJSON = "Jackson.json";
    private static Person p1;
    private static Person p2;

    public static void main(String[] args) {
        rewriteJSONUsingJackson(parseToObjectUsingJackson());
    }

    private static ArrayList parseToObjectUsingJackson() {
        ArrayList personList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            personList = objectMapper.readValue(new File(PATH + FILETOOBJECT), ArrayList.class);
            for (Object tmp : personList) {
                System.out.println(tmp);
                System.out.println("\n");
                System.out.println(tmp.getClass().getName());
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        return personList;
    }

    public static void writeJSONUsingJackson(ArrayList<Person> personArrayList) {
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

    private static void rewriteJSONUsingJackson(ArrayList<Person> personArrayList) {
        ObjectMapper objectMapper = new ObjectMapper();
        List frl = new ArrayList();
        List taglist = new ArrayList();
        p1 = new Person("1", "2", 2411341.2, "efg", "rqef", 124, "wafad", false, "dgsgs", frl, taglist, "fav", "al", "111", "222", "333", "44", "555", "11111", 34, "sag", 15.2);
        p2 = new Person("2", "2", 2411341.2, "efg", "rqef", 124, "wafad", false, "dgsgs", frl, taglist, "fav", "al", "111", "222", "333", "44", "555", "22222", 34, "sag", 15.2);
        personArrayList.add(p1);
        personArrayList.add(p2);

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