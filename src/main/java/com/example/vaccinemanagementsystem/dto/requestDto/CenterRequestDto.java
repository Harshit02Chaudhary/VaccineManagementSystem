package com.example.vaccinemanagementsystem.dto.requestDto;

import com.example.vaccinemanagementsystem.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CenterRequestDto {

    String centerName;

    CenterType centerType;

    String address;
}
