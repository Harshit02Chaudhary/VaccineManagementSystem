package com.example.vaccinemanagementsystem.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookAppointmentResponseDto {

    String personName;

    String doctorName;

    String appointmentID;

    Date appointmentDate;

    CenterResponseDto centerResponseDto;

}
