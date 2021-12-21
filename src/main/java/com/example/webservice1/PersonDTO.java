package com.example.webservice1;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class PersonDTO {
    String id;
    String firstName;
    String lastName;
    List<GroupDTO> Groups;

}
