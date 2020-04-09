/**
 * Наследование от класса Thread
 */
public class ThreadsEx extends Thread {
    String row = "Hello_world";

    public ThreadsEx(String name) {
        super(name);
    }

    public void run() {
        System.out.println(row.charAt((int) (Math.random() * 10)));
    }
    //Collections.sort(x,(o1,o2))->s1.compareTo(s2))
}

class Program {
    public static void main(String[] args) {
        System.out.println("Main thread started...");
        for (int i = 1; i < 11; i++)
            new ThreadsEx("Thread " + i).start();
    }
}

/**
 * Реализация Интерфейса Runnable
 */
class ThreadFromLymbdaInterfaceRunnable {
    public static void main(String[] args) {
        String row = "Hello_world";
        Runnable run = () -> {
            System.out.println(row.charAt((int) (Math.random() * 10)));
        };
        for (int i = 0; i < row.length(); i++) {
            new ThreadsEx("Thread " + i + " - " + row.charAt((int) (Math.random() * row.length()))).start();
        }
    }
}

class ThreadFromLymbda {
    public static void main(String[] args) {
        char[] array = "hello_world".toCharArray();
        for (int i = 0; i < array.length; i++) {
            final int index = i;

            new Thread(() -> System.out.println(array[index]))
                    .start();
        }
    }
}