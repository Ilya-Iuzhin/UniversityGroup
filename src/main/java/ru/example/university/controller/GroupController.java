package ru.example.university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.example.university.dto.request.GroupUpdateDTO;
import ru.example.university.dto.UniversityGroupDTO;
import ru.example.university.dto.request.GroupRequestAddDTO;
import ru.example.university.dto.response.GetGroupResponseDTO;
import ru.example.university.service.GroupService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/group")
@RequiredArgsConstructor
public class GroupController {

    private final RestTemplate restTemplate;
    private final GroupService groupService;

    /**
     * Метод для добавления новой группы.
     *
     * @param groupRequestAddDTO объект, содержащий информацию о новой группе
     */
    @PostMapping(path = "/add")
    public void addGroup(@Valid @RequestBody GroupRequestAddDTO groupRequestAddDTO) {
        groupService.add(groupRequestAddDTO);
    }

    /**
     * Метод для получения информации о группе по её идентификатору.
     *
     * @param id идентификатор группы
     * @return объект GetGroupResponseDTO, содержащий информацию о запрошенной группе
     */
    @GetMapping(path = "/get")
    public GetGroupResponseDTO getGroup(@RequestParam Long id) {
        return groupService.get(id);
    }

    /**
     * Метод для поиска группы по идентификатору и имени.
     *
     * @param id   идентификатор группы
     * @param name имя группы
     * @return объект ResponseEntity с объектом GetGroupResponseDTO в теле ответа и статусом 200 OK,
     * если группа найдена; либо объект ResponseEntity с сообщением об ошибке и статусом 404 Not Found,
     * если группа не найдена
     */
    @GetMapping(path = "/search")
    public ResponseEntity<Object> findEntityByIdAndName(@RequestParam Long id, @RequestParam String name) {
        try {
            GetGroupResponseDTO getGroupResponseDTO = groupService.getGroupForIdAndName(id, name);
            return ResponseEntity.ok(getGroupResponseDTO);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    /**
     * Получает список объектов UniversityGroupDTO, представляющих университетские группы в Китае.
     * Этот метод запрашивает groupService, чтобы найти университетские группы в зависимости от их местоположения в Китае.
     *
     * @return Список объектов UniversityGroupDTO, представляющих университетские группы в Китае.
     */
    @GetMapping("/china")
    public List<UniversityGroupDTO> getGroupsInChina() {

        return groupService.findGroupsByChinaName();
    }

    /**
     * Метод для обновления названия университетской группы.
     *
     * @param groupUpdateDTO объект GroupUpdateDTO, содержащий информацию об обновлении названия группы
     * @return объект ResponseEntity с обновленным объектом GroupUpdateDTO в теле ответа
     */
    @PutMapping("/rename")
    public ResponseEntity<GroupUpdateDTO> updateUniversityGroup(@RequestBody GroupUpdateDTO groupUpdateDTO) {
        return ResponseEntity.ok(groupService.updateGroup(groupUpdateDTO));
    }
}
