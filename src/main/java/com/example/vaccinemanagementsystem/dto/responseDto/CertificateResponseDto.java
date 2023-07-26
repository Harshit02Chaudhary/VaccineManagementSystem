package com.example.vaccinemanagementsystem.dto.responseDto;

import com.example.vaccinemanagementsystem.model.Person;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CertificateResponseDto {

    String certificateNo;

    String confirmationMessage;

    Person person;
}
