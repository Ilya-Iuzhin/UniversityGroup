package ru.example.university.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.university.dto.StudentDeleteDTO;
import ru.example.university.dto.request.StudentRequestDTO;
import ru.example.university.dto.response.GetStudentResponseDTO;
import ru.example.university.service.StudentService;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    /**
     * Метод для добавления нового студента
     *
     * @param studentRequestDTO объект с данными о студенте для добавления
     */
    @PostMapping(path = "/add")
    public void add(@RequestBody StudentRequestDTO studentRequestDTO) {
        studentService.addStudent(studentRequestDTO);
    }

    /**
     * Метод для удаления студента по его идентификатору и имени.
     *
     * @param id   идентификатор студента
     * @param name имя студента
     * @return объект ResponseEntity без содержимого (No Content) в случае успешного удаления
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@RequestParam Long id, @RequestParam String name) {
        studentService.delete(id, name);
        return ResponseEntity.noContent().build();
    }

    /**
     * Метод получения информации о студенте по городу и возрасту.
     *
     * @param city Город, в котором проживает студент.
     * @param age  Возраст студента.
     * @return ResponseEntity с данными о студенте в формате GetStudentResponseDTO и статусом HttpStatus.OK в случае получения запроса на выполнение.
     */
    @GetMapping("/get")
    public ResponseEntity<?> getStudentForCityAndAge(@RequestParam String city, @RequestParam Integer age) {
        GetStudentResponseDTO responseDTO = studentService.getStudentForCityAndAge(city, age);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}