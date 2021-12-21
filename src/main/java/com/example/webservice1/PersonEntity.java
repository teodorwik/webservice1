package com.example.webservice1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "person")
public class PersonEntity {
    @Id
    String id;
    String firstName;
    String lastName;

    @ElementCollection
    List<String> groups;


    public PersonEntity(String firstName, String lastName, List<String> groups) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        groups = groups;
    }
}
