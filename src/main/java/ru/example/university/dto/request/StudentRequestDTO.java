package ru.example.university.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentRequestDTO {

    private String name;
    private Long groupId;
    private String firstName;
}
