package com.example.webservice1;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepository {
    Map<String, PersonEntity> persons = new HashMap<>();

    public List<PersonEntity> findAll() {
        return new ArrayList<>(persons.values());
    }

    public Optional<PersonEntity> findById(String personId) {
        return Optional.of(persons.get(personId));
    }

    public PersonEntity save(PersonEntity person) {
        persons.put(person.getId(), person);
        return person;
    }

    public void delete(String id) {
        persons.remove(id);
    }


}

