package com.example.vaccinemanagementsystem.service;

import com.example.vaccinemanagementsystem.dto.requestDto.CenterRequestDto;
import com.example.vaccinemanagementsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinemanagementsystem.model.VaccinationCenter;
import com.example.vaccinemanagementsystem.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationRepository vaccinationRepository;

    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        VaccinationCenter center = new VaccinationCenter();
        center.setCenterName(centerRequestDto.getCenterName());
        center.setCenterType(centerRequestDto.getCenterType());
        center.setAddress(centerRequestDto.getAddress());

        VaccinationCenter savedCenter = vaccinationRepository.save(center);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterAddress(savedCenter.getAddress());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setMessage("Vaccination Center is added");

        return centerResponseDto;
    }
}
