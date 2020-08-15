
/**
 * A class to hold  Person details.
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class Person {
    //the first name of person
    private String firstName;
    //the last name of person
    private String lastName;

    /**
     * Create a new person with a given first name and last name.
     *
     * @param firstName first name of person.
     * @param lastName last name of person.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * get The first name of person.
     * @return firstName field.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get The last name of person.
     * @return lastName field.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * get a String of person's information.
     * @return a String.
     */
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }
}
