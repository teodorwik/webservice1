package com.example.webservice1;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    PersonService personService;
    PersonRepository personRepository;

    @GetMapping("/get/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable(value= "id") String id ) throws Exception{
        PersonDTO personDTO = personService.getPersonDto(id);
        return ResponseEntity.ok().body(personDTO);
    }

    @PostMapping("/create")
    public PersonEntity createPerson(@Valid @RequestBody PersonEntity person){
        person.setId(UUID.randomUUID().toString());
        return personRepository.save(person);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable(value ="id") String id) throws Exception {
        return personService.deleteMember(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable(value = "id") String id, @Valid @RequestBody PersonEntity person) throws Exception{
        final PersonEntity updatePerson = personService.getPersonEntity(id, person);
        return ResponseEntity.ok(updatePerson);
    }
}
