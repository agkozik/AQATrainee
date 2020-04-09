public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("HelloWorld from Java Application running in Docker");
            Thread.sleep(1000);
        }
    }
}