package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import jackson.Person;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class GsonParseExample {
    private static final String PATH = "C:\\DZ\\AQATrainee\\JsonParsersTrainee\\src\\main\\resources\\";
    private static final String FILETOOBJECT = "json.txt";
    private static final String STRINGTOJSON = "Gson.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static ArrayList<Person> persons;

    public static void main(String[] args) {
        readJson();
        addPersonToJson(persons);
        writeJsonToFile(persons);
    }

    public static ArrayList<Person> readJson() {
        try {
            JsonReader reader = new JsonReader(new FileReader(PATH + FILETOOBJECT));
            Type userListType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            persons = gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
        return persons;
    }

    public static void writeJsonToFile(ArrayList<Person> persons) {
        String personsFromJson = gson.toJson(persons);
        try {
            Files.write(Paths.get(PATH + STRINGTOJSON), personsFromJson.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу: " + e.getMessage());
        }
    }

    public static ArrayList<Person> addPersonToJson(ArrayList<Person> persons) {
        Person p1 = persons.get(0);
        Person p2 = persons.get(1);
        persons.add(p1);
        persons.add(p2);
        return persons;
    }
}