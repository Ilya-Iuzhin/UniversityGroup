package ru.example.university.service;

import ru.example.university.dto.request.StudentRequestDTO;
import ru.example.university.dto.response.GetStudentResponseDTO;

public interface StudentService {
    void addStudent(StudentRequestDTO studentRequestDTO);
    void delete(Long id, String name);
    GetStudentResponseDTO getStudentForCityAndAge(String city, Integer age);
}
