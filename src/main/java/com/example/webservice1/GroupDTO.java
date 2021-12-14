package com.example.webservice1;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class GroupDTO {
    String id;
    String groupName;
    List<PersonDTO> members;

    public GroupDTO(String groupName) {
        this.id = UUID.randomUUID().toString();
        this.groupName = groupName;
        this.members = new ArrayList<>(); //ArrayList
    }
}
