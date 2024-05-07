package ru.example.university.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityGroupDTO {

    private String name;
    private LocalDate localDate;
}
