package com.example.vaccinemanagementsystem.dto.requestDto;

import com.example.vaccinemanagementsystem.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddPersonRequestDto {

    String name;

    int age;

    @Column(unique = true)
    String email;

    Gender gender;

}
