package com.example.vaccinemanagementsystem.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookAppointmentRequestDto {

    int personId;

    int doctorId;

}
