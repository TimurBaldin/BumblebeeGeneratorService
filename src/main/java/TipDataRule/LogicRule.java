package TipDataRule;
import Rules.ManagerRule;
import com.github.javafaker.Faker;

import java.util.Locale;

public class LogicRule {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("EN"));

        String name = faker.name().fullName();
        System.out.println("Name "+name);

    }
}
