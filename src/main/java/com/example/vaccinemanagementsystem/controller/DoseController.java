package com.example.vaccinemanagementsystem.controller;

import com.example.vaccinemanagementsystem.dto.requestDto.BookDose1RequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.BookDose1ResponseDto;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

//    @PostMapping("/getDose1")
//    public ResponseEntity<Object> getDose1(@RequestParam("id") int personId, @RequestParam("doseType") DoseType doseType) throws PersonNotFoundException {
//
//        try {
//            Dose doseTake = doseService.getDose1(personId, doseType);
//            return new ResponseEntity<>(doseTake, HttpStatus.CREATED);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/getDose1")
    public ResponseEntity<Object> getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto) throws PersonNotFoundException {

        try {
            BookDose1ResponseDto doseTake = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity<>(doseTake, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getDose2")
    public ResponseEntity<Object> getDose2(@RequestBody BookDose1RequestDto bookDose1RequestDto) throws PersonNotFoundException {

        try {
            BookDose1ResponseDto doseTake = doseService.getDose2(bookDose1RequestDto);
            return new ResponseEntity<>(doseTake, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
