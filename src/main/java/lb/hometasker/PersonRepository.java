package lb.hometasker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {
    private List<Person> personList;

    public PersonRepository() {
        personList = new ArrayList<>();
        personList.add(new Person("Anna"));
        personList.add(new Person("Piotr"));
        personList.add(new Person("Zosia"));
        personList.add(new Person("Jaś"));

    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Person findByName(String name){
        for (Person person : personList) {
            if(person.getName().equals(name)){
                return person;
            }
        }
        return null;
    }

    public void addPerson(Person person){
        personList.add(person);
    }

    public void removePerson (Person person){
        personList.remove(person);
    }





}
