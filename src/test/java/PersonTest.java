import org.fasttrackit.Person;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PersonTest {
    @Test
    public void nameTest(){
        //GIVEN
        Person person = new Person(1, "Ion", 44);
        //WHEN
        person.setName("Mihai");
        //THEN
        assertThat(person.getName()).isEqualTo("Mihai");
    }

    @Test
    public void ageTest(){
        //GIVEN
        Person person = new Person(1, "Ion", 44);
        //WHEN
        person.setAge(44);
        //THEN
        assertThat(person.getAge()).isEqualTo(44);
    }

    @Test
    public void idTest(){
        //GIVEN
        int newId = 5;
        Person person = new Person(1, "Ion", 44);
        //WHEN
        person.setId(newId);
        //THEN
        assertThat(person.getId()).isEqualTo(newId);
    }
}
