package multithreading;

public class Threads {
    public static int randomValue;
    public static int guesValue;
    public static boolean isPlay = true;

    public static void main(String[] args) {
        randomValue = (int) (Math.random() * 100000000);

        Thread counter = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                try {
                    while (isPlay) {
                        System.out.println(i + " first");
                        i++;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        counter.start();

        Thread counter2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlay) {
                    guesValue = (int) (Math.random() * 100000000);
                    if (guesValue == randomValue) {
                        isPlay = false;
                        System.out.println(randomValue + " equal " + guesValue);
                    }
                }
            }
        });
        counter2.start();
    }
}