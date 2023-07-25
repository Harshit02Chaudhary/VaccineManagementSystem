package com.example.vaccinemanagementsystem.controller;

import com.example.vaccinemanagementsystem.dto.requestDto.AddPersonRequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.AddPersonResponseDto;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Person;
import com.example.vaccinemanagementsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

//    @PostMapping("/add")
//    public ResponseEntity addPerson(@RequestBody Person person) {
//        try {
//            Person personResponse = personService.addPerson(person);
//            return new ResponseEntity(personResponse, HttpStatus.CREATED);
//        }
//        catch (Exception e) {
//            return new ResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto) {
        try {
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail) throws PersonNotFoundException {
        try {
            String emailUpdate = personService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(emailUpdate, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //get dose 2

}
