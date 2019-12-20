import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample {
    private static final String PATH = "src//main//resources//";
    private static final String INPUTFILE = "Input.txt";
    private static final String OUTPUTFILE = "Output.txt";

    public static void main(String[] args) {
        String newData=arrayProcessing(readFromFile());
        writeFile(newData);
    }

    /**
     * Чтение из файла Input.txt
     *
     * @return
     */
    public static byte[] readFromFile() {
        byte[] buffer = new byte[0];
        try (FileInputStream fis = new FileInputStream(PATH + INPUTFILE)) {
            buffer = new byte[fis.available()];
            fis.read(buffer, 0, buffer.length);
            System.out.println("\nФайл прочитан!");
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл " + INPUTFILE + e.getMessage());
        }
        return buffer;
    }

    /**
     * Обработка массива char (все первые буквы слов в Верхний регистр), возврат результата в виде String
     *
     * @return
     */
    public static String arrayProcessing(byte[] buffer) {
        char temp[] = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            temp[i] = (char) buffer[i];
            if (i == 0) {
                temp[0] = Character.toUpperCase(temp[0]);
            }
        }
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == ' ') {
                temp[i + 1] = Character.toUpperCase(temp[i + 1]);
            }
        }
        System.out.println("Файл обработан!");
        return String.valueOf(temp);
    }

    public static void writeFile(String tmp) {
        try (FileOutputStream fos = new FileOutputStream(PATH + OUTPUTFILE)) {
            byte[] buffer = tmp.getBytes();
            fos.write(buffer, 0, buffer.length);
            System.out.println("Файл записан!");

        } catch (IOException e) {
            System.out.println("Не удалось записать файл " + INPUTFILE + e.getMessage());
        }
    }
}