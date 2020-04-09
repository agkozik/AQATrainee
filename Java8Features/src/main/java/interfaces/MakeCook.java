package interfaces;

public class MakeCook {
    public static void main(String[] args) {
        Director director = new Director();
       Cooker cooker = new Cooker();
        director.makeCook(cooker);

        Coocable cook = () -> System.out.println("dg");

        director.makeCook(new Coocable() {
            @Override
            public void cook() {
                System.out.println("Finish");
            }
        });

        for (int i = 0; i <10000 ; i++) {
            int random = 10+(int)(Math.random()*91);

            if (random<=10)
                System.out.println(random);
        }
    }
}