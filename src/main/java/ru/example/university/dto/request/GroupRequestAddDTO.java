package ru.example.university.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestAddDTO {

    @NotBlank
    @NotNull
    private String name;
    private Long id;
}
