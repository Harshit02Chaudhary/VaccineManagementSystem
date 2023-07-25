package com.example.vaccinemanagementsystem.repository;

import com.example.vaccinemanagementsystem.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRepository extends JpaRepository<Dose, Integer> {

}
