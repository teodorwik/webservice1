package com.example.webservice1;

import org.springframework.stereotype.Service;

@Service
public class PersonService {
    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    public PersonEntity getPersonEntity(String id, PersonEntity person) throws Exception{
        PersonEntity personEntity = personRepository.findById(id).orElseThrow();

        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        return personRepository.save(personEntity);
    }

    public String deletePerson(String id) throws Exception{
        PersonEntity person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
        return id;
    }
}
