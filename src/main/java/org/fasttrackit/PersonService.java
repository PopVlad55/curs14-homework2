package org.fasttrackit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonService {
    private final Map<Integer, Person> persons = new HashMap<>();
    private int currentId = 1;

    public Person addPerson(Person person){
        int generatedId = currentId++;
        person.setId(generatedId);
        persons.put(generatedId, person);
        return person;
    }
    public Person removePerson(int id){
        if(!persons.containsKey(id)){
            throw new PersonNotFoundException("Person with id " + id + " not found");
        }

        Person removedPerson = persons.remove(id);
        return removedPerson;
    }

    public List<Person> getAllPersons(){
        return new ArrayList<>(persons.values());
    }

    public List<Person> getPersonsOlderThan(int age){
        if(age <= 0 || age >= 120){
            throw new IllegalArgumentException("Invalid age");
        }
        List<Person> olderPersons = new ArrayList<>();
        for (Person person : persons.values()){
            if(person.getAge() > age){
                olderPersons.add(person);
            }
        }
        return olderPersons;
    }

    public List<String> getAllPersonNames(){
        List<String> personNames = new ArrayList<>();
        for(Person person : persons.values()){
            personNames.add(person.getName());
        }
        return personNames;
    }

    public Person getPerson(String name){
        return persons.values().stream()
                .filter(person -> person.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Person getPersonById(int id){
        return persons.get(id);
    }

    public static class PersonNotFoundException extends RuntimeException{
        public PersonNotFoundException(String message){
            super(message);
        }
    }
}
