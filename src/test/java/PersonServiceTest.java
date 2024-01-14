import org.fasttrackit.Person;
import org.fasttrackit.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonService();
    }

    @Test
    public void addPersonAndIdTest() {
        //GIVEN
        Person person = new Person(0, "Mihai", 25);

        //WHEN
        Person addedPerson = personService.addPerson(person);

        //THEN
        assertThat(addedPerson.getId()).isGreaterThan(0);
        assertThat(addedPerson.getName()).isEqualTo("Mihai");
        assertThat(addedPerson.getAge()).isEqualTo(25);
    }

    @Test
    public void removePersonTest() {
        //GIVEN
        Person person = new Person(1, "Andrei", 30);
        personService.addPerson(person);

        //WHEN
        Person removedPerson = personService.removePerson(1);

        //THEN
        assertThat(removedPerson).isEqualTo(person);
        assertThat(personService.getAllPersons()).isEmpty();
    }

    @Test
    public void removePersonNullIdTest() {
        assertThrows(PersonService.PersonNotFoundException.class, () -> {
            personService.removePerson(1);
        });
    }

    @Test
    public void returnAllPersonsTest() {
        //GIVEN
        Person person1 = new Person(1, "Andrei", 30);
        Person person2 = new Person(2, "Ion", 35);
        personService.addPerson(person1);
        personService.addPerson(person2);

        //WHEN
        List<Person> allPersons = personService.getAllPersons();

        //THEN
        assertThat(allPersons).containsExactly(person1, person2);
    }

    @Test
    public void getPersonsOlderThanTest() {
        //GIVEN
        Person person1 = new Person(1, "Andrei", 25);
        Person person2 = new Person(2, "Ion", 30);
        personService.addPerson(person1);
        personService.addPerson(person2);

        //WHEN
        List<Person> olderPersons = personService.getPersonsOlderThan(25);

        //THEN
        assertThat(olderPersons).containsExactly(person2);
    }

    @Test
    public void getPersonsOlderThanInvalidAgeTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            personService.getPersonsOlderThan(120);
        });
    }

    @Test
    public void returnAllPersonNamesTest() {
        //GIVEN
        Person person1 = new Person(1, "Andrei", 30);
        Person person2 = new Person(2, "Ion", 35);
        personService.addPerson(person1);
        personService.addPerson(person2);

        //WHEN
        List<String> allPersonNames = personService.getAllPersonNames();

        //THEN
        assertThat(allPersonNames).containsExactly("Andrei", "Ion");
    }

    @Test
    public void returnPersonByNameTest() {
        //GIVEN
        Person person1 = new Person(1, "Andrei", 30);
        Person person2 = new Person(2, "Ion", 35);
        personService.addPerson(person1);
        personService.addPerson(person2);

        //WHEN
        Person foundPerson = personService.getPerson("Andrei");

        //THEN
        assertThat(foundPerson).isEqualTo(person1);
    }

    @Test
    public void returnNullIFNoName() {
        //WHEN
        Person foundPerson = personService.getPerson("David");

        //THEN
        assertThat(foundPerson).isNull();
    }

    @Test
    public void returnPersonByIdTest(){
        //GIVEN
        Person person1 = new Person(1, "Andrei", 30);
        personService.addPerson(person1);
        //WHEN
        Person foundPerson = personService.getPersonById(1);
        //THEN
        assertThat(foundPerson).isEqualTo(person1);
    }

    @Test
    public void returnNullForNoId(){
        //WHEN
        Person foundPerson = personService.getPersonById(2);
        //THEN
        assertThat(foundPerson).isNull();
    }

}
