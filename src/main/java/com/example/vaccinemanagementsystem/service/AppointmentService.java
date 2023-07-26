package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.dto.requestDto.BookAppointmentRequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.BookAppointmentResponseDto;
import com.example.vaccinemanagementsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinemanagementsystem.exception.DoctorNotFoundException;
import com.example.vaccinemanagementsystem.exception.PersonNotFoundException;
import com.example.vaccinemanagementsystem.model.Appointment;
import com.example.vaccinemanagementsystem.model.Doctor;
import com.example.vaccinemanagementsystem.model.Person;
import com.example.vaccinemanagementsystem.model.VaccinationCenter;
import com.example.vaccinemanagementsystem.repository.AppointmentRepository;
import com.example.vaccinemanagementsystem.repository.DoctorRepository;
import com.example.vaccinemanagementsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) throws PersonNotFoundException {

        Optional<Person> personOptional = personRepository.findById(bookAppointmentRequestDto.getPersonId());
        if(!personOptional.isPresent()) {
            throw new PersonNotFoundException("Invalid Person ID");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());
        if(!doctorOptional.isPresent()) {
            throw new DoctorNotFoundException("Invalid Doctor ID");
        }

        Person person = personOptional.get();
        Doctor doctor = doctorOptional.get();

        //create appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentNo(String.valueOf(UUID.randomUUID()));

        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        //when multiple parents saving child entity you need to make sure child is saved only once
        //Save the child first using child repository
        Appointment savedAppointment = appointmentRepository.save(appointment);
        //-----Whenever multiple entities are in your API check for any change required in any other entities or not-----//
        doctor.getAppointmentList().add(savedAppointment);
        person.getAppointments().add(savedAppointment);

        Doctor savedDoctor = doctorRepository.save(doctor);
        Person savedPerson = personRepository.save(person);
        VaccinationCenter center = savedDoctor.getVaccinationCenter();

        String text = "Congrats! " + savedPerson.getName()+" Your Appointment has been booked with Doctor " +
                        savedDoctor.getName() + ". Your Vaccination Center name is " + center.getCenterName() +
                        " Please reach at this address " + center.getAddress() + " at this time: " +
                        savedAppointment.getAppointmentDate() + ". See you there!!!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("johnwickbabayaga78@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmail());
        simpleMailMessage.setSubject("Congrats! Appointment Done");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterAddress(center.getAddress());
        centerResponseDto.setCenterType(center.getCenterType());
        centerResponseDto.setCenterName(center.getCenterName());

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setAppointmentID(savedAppointment.getAppointmentNo());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

        return bookAppointmentResponseDto;
    }
}
