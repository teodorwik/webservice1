package com.example.webservice1;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public String deletePerson(String id) throws Exception{
        PersonEntity person = personRepository.findById(id).orElseThrow();
        personRepository.delete(person);
        return id;
    }

    public String deleteMember(String memberId) throws Exception{
        PersonEntity person = personRepository.findById(memberId).orElseThrow();
        personRepository.delete(person);

        WebClient webClient = WebClient.create("http://localhost:8080");
        String member = webClient
                .delete()
                .uri("/groups/deleteMember/"+memberId)
                .header("header","key")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return member;
    }


    public PersonDTO getPersonDto(String id) throws Exception{
        PersonEntity person = personRepository.findById(id).orElseThrow();

        WebClient webClient = WebClient.create("http://localhost:8080");
        List<GroupDTO> groups = person.groups.stream()
                .map(groupId -> {
                    return webClient.get()
                            .uri("/groups/get" + person.groups)
                            .header("header", "key")
                            .retrieve()
                            .bodyToMono(GroupDTO.class)
                            .block();
                })
                .collect(Collectors.toList());
        PersonDTO personDTO = new PersonDTO(person.id, person.firstName, person.lastName, groups);
        return personDTO;
    }


}
