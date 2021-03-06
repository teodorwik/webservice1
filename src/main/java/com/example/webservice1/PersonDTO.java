package com.example.webservice1;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PersonDTO {
    String id;
    String firstName;
    String lastName;
    List<GroupDTO> Groups;

    public PersonDTO(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        Groups = new ArrayList<>();
    }
}
