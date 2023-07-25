package com.example.vaccinemanagementsystem.repository;

import com.example.vaccinemanagementsystem.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationCenter, Integer> {
}
