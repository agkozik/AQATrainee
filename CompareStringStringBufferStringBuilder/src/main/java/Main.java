import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int ITERATIONS = 10000;

    public static void main(String[] args) {
        test(new StringBuilder());
        test(new StringBuffer());
        test(new String());

    }

    public static void test(StringBuilder sb) {
        StringBuilder result =new StringBuilder();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            String s1 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s2 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s3 = String.valueOf((int) (Math.random() * ITERATIONS));
            result=result.append(s1).append(s2).append(s3);
        }
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " - StringBuilder");
    }

    public static void test(StringBuffer sbf) {
        StringBuffer result = new StringBuffer();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            String s1 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s2 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s3 = String.valueOf((int) (Math.random() * ITERATIONS));

            result=result.append(s1).append(s2).append(s3);
        }
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " - StringBuffer");
    }

    public static void test(String str) {
        long startTime = System.currentTimeMillis();
        String result = null;
        for (int i = 0; i < ITERATIONS; i++) {
            String s1 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s2 = String.valueOf((int) (Math.random() * ITERATIONS));
            String s3 = String.valueOf((int) (Math.random() * ITERATIONS));

            result += s1 + s2 + s3;
        }
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " - String");
    }
}