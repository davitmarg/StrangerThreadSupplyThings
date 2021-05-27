import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Person() {
        Faker faker = new Faker();
        this.id = UUID.randomUUID().toString();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
    }

    public List<String> toStringList()
    {
        List<String> list = new ArrayList<>();
        list.add(id+"");
        list.add(firstName);
        list.add(lastName);
        list.add(email);
        return list;
    }

}
