package ru.example.university.service;

import ru.example.university.dto.request.GroupUpdateDTO;
import ru.example.university.dto.UniversityGroupDTO;
import ru.example.university.dto.request.GroupRequestAddDTO;
import ru.example.university.dto.response.GetGroupResponseDTO;
import java.util.List;

public interface GroupService {
    void add(GroupRequestAddDTO groupRequestAddDTO);

    GetGroupResponseDTO getGroupForIdAndName(Long id, String name);


    GroupUpdateDTO updateGroup( GroupUpdateDTO groupUpdateDTO);

    List<UniversityGroupDTO> findGroupsByChinaName();

    GetGroupResponseDTO get(Long id);



}
