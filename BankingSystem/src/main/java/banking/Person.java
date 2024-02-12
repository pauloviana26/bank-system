package banking;

import java.util.Random;

/**
 * The concrete Account holder of Person type.
 */
public class Person {
    private String firstName;
    private String lastName;

    private int idNumber;

    public Person(String firstName, String lastName, int idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }
}
