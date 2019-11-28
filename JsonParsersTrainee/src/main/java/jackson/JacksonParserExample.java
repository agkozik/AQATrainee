package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class JacksonParserExample {
    private static final String PATH = "C:\\DZ\\AQATrainee\\JsonParsersTrainee\\src\\main\\resources\\";
    private static final String FILETOOBJECT = "json.txt";
    private static final String STRINGTOJSON = "Jackson.json";

    public static void main(String[] args) {
        writeJSONUsingJackson(parseToObjectUsingJackson());
    }

    public static Person[] parseToObjectUsingJackson() {
        Person person = null;
        Person[] persons = new Person[0];
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            persons = objectMapper.readValue(new File(PATH + FILETOOBJECT), Person[].class);
            for (Person tmp : persons) {
                System.out.println(tmp);
                System.out.println("\n");
                System.out.println(tmp.getFriends());
                System.out.println("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        return persons;
    }

    public static void writeJSONUsingJackson(Person[] persons) {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = null;
        try {
            jsonString = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(persons);
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