package lyambdas;

public class Player {
    private String name;
    private String race;
    private double health;
    private int age;

    public Player(String name, String race, double health, int age) {
        this.name = name;
        this.race = race;
        this.health = health;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", health=" + health +
                ", age=" + age +
                '}';
    }
}