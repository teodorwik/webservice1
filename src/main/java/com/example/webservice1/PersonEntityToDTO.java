package com.example.webservice1;

import org.springframework.stereotype.Service;

@Service
public interface PersonEntityToDTO {
    default PersonEntity PersonEntityToDTO(PersonEntity personEntity){
        return new PersonEntity(
                personEntity.getId(),
                personEntity.firstName,
                personEntity.getLastName(),
                personEntity.Groups
        );
    }
}
