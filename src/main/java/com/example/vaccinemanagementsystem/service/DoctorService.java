package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.dto.requestDto.DoctorRequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinemanagementsystem.dto.responseDto.DoctorResponseDto;
import com.example.vaccinemanagementsystem.exception.CenterNotFoundException;
import com.example.vaccinemanagementsystem.model.Doctor;
import com.example.vaccinemanagementsystem.model.VaccinationCenter;
import com.example.vaccinemanagementsystem.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    VaccinationRepository vaccinationRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> vcOptional = vaccinationRepository.findById(doctorRequestDto.getCenterId());

        if(vcOptional.isEmpty()) {
            throw new CenterNotFoundException("Sorry, Center not found");
        }

        VaccinationCenter center = vcOptional.get();

        //convert Dto->Entity
        Doctor doctor = new Doctor();
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmail(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setName(doctorRequestDto.getName());
        doctor.setVaccinationCenter(center);

        //add in center's doctor list
        center.getDoctor().add(doctor);

        //this will save both center & doctor
        VaccinationCenter savedCenter = vaccinationRepository.save(center);

        List<Doctor> doctors = center.getDoctor();
        Doctor savedDoctor = doctors.get(doctors.size()-1);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setCenterAddress(savedCenter.getAddress());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(savedDoctor.getName());
        doctorResponseDto.setMessage("Congrats! You have been registered");
        doctorResponseDto.setCenterResponseDto(centerResponseDto);

        return doctorResponseDto;
    }
}
