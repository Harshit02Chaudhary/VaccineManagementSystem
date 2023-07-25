package com.example.vaccinemanagementsystem.dto.responseDto;

import com.example.vaccinemanagementsystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDose1ResponseDto {
    String name;

    DoseType doseType;
}
