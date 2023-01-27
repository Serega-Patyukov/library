package com.example.library.repository;

import com.example.library.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    boolean existsByName(String name);
    Person findByName(String name);
}
