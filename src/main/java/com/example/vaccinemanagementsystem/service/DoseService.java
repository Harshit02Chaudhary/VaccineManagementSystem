package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.Enum.DoseType;
import com.example.vaccinemanagementsystem.dto.requestDto.BookDose1RequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.BookDose1ResponseDto;
import com.example.vaccinemanagementsystem.exception.DoseAlreadyTakenException;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Dose;
import com.example.vaccinemanagementsystem.model.Person;
import com.example.vaccinemanagementsystem.repository.DoseRepository;
import com.example.vaccinemanagementsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PersonRepository personRepository;

    public BookDose1ResponseDto getDose1(BookDose1RequestDto bookDose1RequestDto) throws PersonNotFoundException {

        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());
        if(!optionalPerson.isPresent()) {
            throw new PersonNotFoundException("Invalid Person");
        }

        Person person = optionalPerson.get();

        if(person.isDose1taken()) {
            throw new DoseAlreadyTakenException("Dose 1 already taken");
        }

        Dose dose = new Dose();
        dose.setDoseNo(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1taken(true);
        person.getDosesTaken().add(dose);

        Person savedPerson = personRepository.save(person);

        BookDose1ResponseDto bookDose1ResponseDto = new BookDose1ResponseDto();
        bookDose1ResponseDto.setName(person.getName());
        bookDose1ResponseDto.setDoseType(dose.getDoseType());
        return bookDose1ResponseDto;
    }
}
