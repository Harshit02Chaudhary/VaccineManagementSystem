package com.example.vaccinemanagementsystem.dto.responseDto;

import com.example.vaccinemanagementsystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CenterResponseDto {
    String centerName;

    CenterType centerType;

    String centerAddress;

    String message;
}
