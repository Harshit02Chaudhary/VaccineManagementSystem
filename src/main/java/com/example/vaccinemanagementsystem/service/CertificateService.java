package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.dto.responseDto.CertificateResponseDto;
import com.example.vaccinemanagementsystem.exception.DoseNotTakenException;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Certificate;
import com.example.vaccinemanagementsystem.model.Person;
import com.example.vaccinemanagementsystem.repository.CertificateRepository;
import com.example.vaccinemanagementsystem.repository.PersonRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;


@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    JavaMailSenderImpl sender;

    public CertificateResponseDto getCertificate(int personId) throws PersonNotFoundException, MessagingException {

        Optional<Person> personOptional = personRepository.findById(personId);
        if(personOptional.isEmpty()) {
            throw new PersonNotFoundException("Invalid Person");
        }

        Person person = personOptional.get();
        if(!person.isDose1taken()) {
            throw new DoseNotTakenException("Please take Dose 1 first :)");
        }
        if(!person.isDose2taken()) {
            throw new DoseNotTakenException("Please take Dose 2 first :)");
        }

        CertificateResponseDto certificateResponseDto = new CertificateResponseDto();
        //8287149874

        if(person.getCertificate()==null) {
            Certificate certificate = new Certificate();
            certificate.setCertificateNo(String.valueOf(UUID.randomUUID()));
            certificate.setConfirmationMessage("Dose Taken Confirmed");
            certificate.setPerson(person);

            person.setCertificate(certificate);
            Person savedPerson = personRepository.save(person);
            Certificate savedCertificate = savedPerson.getCertificate();

            certificateResponseDto.setCertificateNo(savedCertificate.getCertificateNo());
            certificateResponseDto.setConfirmationMessage(savedCertificate.getConfirmationMessage());
            certificateResponseDto.setPerson(savedCertificate.getPerson());
        }
        else {
            Certificate savedCertificate = person.getCertificate();
            certificateResponseDto.setCertificateNo(savedCertificate.getCertificateNo());
            certificateResponseDto.setConfirmationMessage(savedCertificate.getConfirmationMessage());
            certificateResponseDto.setPerson(savedCertificate.getPerson());
        }
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(person.getEmail());
        helper.setFrom("johnwickbabayaga78@gmail.com");
        helper.setSubject("Certificate for Vaccination");
        helper.setText("Congrats! " + person.getName()+" Your Certificate for Vaccination is attached.");

        FileSystemResource file = new FileSystemResource(new File("E:/Documents/CertificateVaccine.docx"));
        helper.addAttachment("CertificateVaccine.docx", file);

        sender.send(message);

        return certificateResponseDto;

    }
}
