package com.example.webservice1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Webservice1Application {

    public static void main(String[] args) {
        SpringApplication.run(Webservice1Application.class, args);
    }

    @Bean
    public CommandLineRunner firstPeople(PersonRepository personRepository){
        return args -> {
            personRepository.save(new PersonEntity("Teodor", "Wik", null));
            personRepository.save(new PersonEntity("Tove", "Wi", null));
            personRepository.save(new PersonEntity("Tobbe", "Wk", null));
            personRepository.save(new PersonEntity("Pernilla", "W", null));
        };

    }
}
