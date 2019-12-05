import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Cat extends Entity {
    private String name;
    private String color;
    private int age;
}