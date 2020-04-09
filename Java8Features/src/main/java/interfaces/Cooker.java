package interfaces;

public class Cooker implements Coocable {

    @Override
    public void cook() {
        System.out.println("Start cooking...");
    }
}
