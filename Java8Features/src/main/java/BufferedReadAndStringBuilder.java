import java.io.*;

public class BufferedReadAndStringBuilder {

    public static void main(String[] args) throws IOException {
        final String INPUT_FILE = "Java8Features/src/main/resources/input.txt";
        final String OUTPUT_FILE = "Java8Features/src/main/resources/output.txt";
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String inputLine;
        String outputLine;

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter bw = new BufferedWriter(new FileWriter("Java8Features/src/main/resources/output.txt"))) {
            int i = 1;
            while ((inputLine = br.readLine()) != null) {
                if (i != 10) {
                    sb.append(inputLine);
                } else {
                    sb.append(System.lineSeparator());
                    i = 0;
                }
                i++;
            }
            bw.write(String.valueOf(sb));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(OUTPUT_FILE))) {
            while ((outputLine = bufferedReader.readLine()) != null) {
                sb2.append(outputLine);
                sb2.append(System.lineSeparator());
            }
        }
            catch (IOException e) {
                e.printStackTrace();
        }
        System.out.println(sb2.toString());
    }
}