package ru.example.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.university.model.Student;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndName(Long id, String name);
    Optional<Student> findByCityAndAge(String city, Integer age);
}
