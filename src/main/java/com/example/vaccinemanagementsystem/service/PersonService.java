package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.dto.requestDto.AddPersonRequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.AddPersonResponseDto;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Person;
import com.example.vaccinemanagementsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        //Convert RequestDTO -> Entity

        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmail(addPersonRequestDto.getEmail());
        person.setGender(addPersonRequestDto.getGender());
        person.setDose1taken(false);
        person.setDose2taken(false);
        person.setCertificate(null);

        Person savedPerson = personRepository.save(person);

        // Convert saved entity -> responseDto

        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Congrats, You have been Registered");
        return addPersonResponseDto;
    }

    public String updateEmail(String oldEmail, String newEmail) throws PersonNotFoundException {
        Person person = personRepository.findByEmail(oldEmail);
        if(person == null) {
            throw new PersonNotFoundException("Sorry, Email doesn't exist");
        }

        person.setEmail(newEmail);
        personRepository.save(person);
        return "Congrats, Email Updated";
    }
}
