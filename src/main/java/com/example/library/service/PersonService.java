package com.example.library.service;

import com.example.library.entity.Person;
import com.example.library.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(Person person) {
        if (personRepository.existsByName(person.getName())) {
            return null;
        }
        return personRepository.save(person);
    }

    public Person editPerson(Person person) {
        if (personRepository.existsByName(person.getName())) {
            Person p = personRepository.findByName(person.getName());
            if (person.getId() != p.getId()) return null;
        }
        return personRepository.save(person);
    }

    public Person getPerson(int id) {
        return personRepository.findById(id).get();
    }

    public List<Person> findAllPerson() {
        return (List<Person>) personRepository.findAll();
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}
