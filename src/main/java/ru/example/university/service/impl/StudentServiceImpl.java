package ru.example.university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.university.dto.request.StudentRequestDTO;
import ru.example.university.dto.response.GetStudentResponseDTO;
import ru.example.university.model.Student;
import ru.example.university.model.UniversityGroup;
import ru.example.university.repository.GroupRepository;
import ru.example.university.repository.StudentRepository;
import ru.example.university.service.StudentService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public void addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setSurname(studentRequestDTO.getFirstName());
        Long groupId = studentRequestDTO.getGroupId();
        UniversityGroup group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        student.setGroup(group);
        studentRepository.save(student);
    }

    public void delete(Long id, String name) {
        Student student = studentRepository.findByIdAndName(id, name)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        studentRepository.delete(student);
    }

    @Override
    public GetStudentResponseDTO getStudentForCityAndAge(String city, Integer age) {
        Optional<Student> studentOptional = studentRepository.findByCityAndAge(city, age);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            GetStudentResponseDTO responseDTO = new GetStudentResponseDTO();
            responseDTO.setCity(student.getCity());
            responseDTO.setAge(student.getAge());
            return responseDTO;
        }
        throw new EntityNotFoundException("Студент не найден");
    }
}
