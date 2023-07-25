package com.example.vaccinemanagementsystem.exception;

import com.example.vaccinemanagementsystem.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public class DoseAlreadyTakenException extends RuntimeException{

    public DoseAlreadyTakenException(String message) {
        super(message);
    }
}
