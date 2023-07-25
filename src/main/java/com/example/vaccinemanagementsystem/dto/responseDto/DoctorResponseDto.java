package com.example.vaccinemanagementsystem.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponseDto {
    String name;

    String message;

    CenterResponseDto centerResponseDto;
}
