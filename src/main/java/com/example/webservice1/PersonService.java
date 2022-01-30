package com.example.webservice1;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public String deletePerson(String id){
        PersonEntity person = personRepository.findById(id).orElseThrow();
        personRepository.delete(id);
        return id;
    }

    public String deleteMember(String memberId){
        PersonEntity person = personRepository.findById(memberId).orElseThrow();
        personRepository.delete(memberId);

        WebClient webClient = WebClient.create("http://localhost:8081");
        String member = webClient
                .delete()
                .uri("/groups/deleteMember/"+memberId)
                .header("header","key")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return member;
    }


    public PersonDTO getPersonDto(String id){
        PersonEntity person = personRepository.findById(id).orElseThrow();
        PersonDTO personDTO = new PersonDTO(person.id, person.firstName, person.lastName);

        WebClient webClient = WebClient.create("http://localhost:8081");
        person.Groups.stream()
                .map(groupId -> webClient.get()
                .uri("/groups/get" + groupId)
                .header("header", "key")
                .retrieve()
                .bodyToMono(GroupDTO.class)
                .block())
                .forEach(group -> personDTO.getGroups().add(group));
        return personDTO;
    }

}
