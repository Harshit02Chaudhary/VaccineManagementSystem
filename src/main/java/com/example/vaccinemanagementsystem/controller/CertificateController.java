package com.example.vaccinemanagementsystem.controller;

import com.example.vaccinemanagementsystem.dto.responseDto.CertificateResponseDto;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Certificate;
import com.example.vaccinemanagementsystem.service.CertificateService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    CertificateService certificateService;

    @GetMapping("/get")
    public ResponseEntity getCertificate(@RequestParam("id") int id) throws PersonNotFoundException, MessagingException {

        try {
            CertificateResponseDto certificate = certificateService.getCertificate(id);
            return new ResponseEntity(certificate, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
