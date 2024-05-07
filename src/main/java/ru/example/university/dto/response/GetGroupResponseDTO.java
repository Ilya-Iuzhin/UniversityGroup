package ru.example.university.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGroupResponseDTO {

    private String name;
    private Long id;
    private LocalDate localDate;
}
