package ru.example.university.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.university.dto.request.GroupUpdateDTO;
import ru.example.university.dto.UniversityGroupDTO;
import ru.example.university.dto.request.GroupRequestAddDTO;
import ru.example.university.dto.response.GetGroupResponseDTO;
import ru.example.university.model.UniversityGroup;
import ru.example.university.repository.GroupRepository;
import ru.example.university.service.GroupService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public void add(GroupRequestAddDTO groupRequestAddDTO) {
        UniversityGroup universityGroup = new UniversityGroup();
        universityGroup.setName(groupRequestAddDTO.getName());
        universityGroup.setLocalDate(LocalDate.now());
        groupRepository.save(universityGroup);
    }

    @Override
    public GetGroupResponseDTO getGroupForIdAndName(Long id, String name) {
        UniversityGroup group = groupRepository.findByIdAndName(id, name);
        if (group != null) {
            GetGroupResponseDTO getGroupResponseDTO = new GetGroupResponseDTO();
            getGroupResponseDTO.setId(group.getId());
            getGroupResponseDTO.setName(group.getName());
            getGroupResponseDTO.setLocalDate(group.getLocalDate());
            return getGroupResponseDTO;
        }
        throw new EntityNotFoundException("Группа не найдена");
    }

    @Transactional
    @Override
    public GroupUpdateDTO updateGroup(GroupUpdateDTO groupUpdateDTO) {
        Long id = groupUpdateDTO.getId();
        UniversityGroup universityGroup = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        universityGroup.setName(groupUpdateDTO.getName());
        UniversityGroup updatedUniversityGroup = groupRepository.save(universityGroup);
        return convertToDTO(updatedUniversityGroup);
    }

    private GroupUpdateDTO convertToDTO(UniversityGroup universityGroup) {
        GroupUpdateDTO dto = new GroupUpdateDTO();
        dto.setId(universityGroup.getId());
        dto.setName(universityGroup.getName());
        return dto;
    }

    @Override
    public List<UniversityGroupDTO> findGroupsByChinaName() {
        List<UniversityGroup> china = groupRepository.onlyChina();
        return convertToDto(china);
    }

    private List<UniversityGroupDTO> convertToDto(List<UniversityGroup> groups) {
        List<UniversityGroupDTO> dtoList = new ArrayList<>();
        for (UniversityGroup group : groups) {
            UniversityGroupDTO dto = new UniversityGroupDTO();
            dto.setLocalDate(group.getLocalDate());
            dto.setName(group.getName());
            dtoList.add(dto);
        }
        return dtoList;

    }


    @Override
    public GetGroupResponseDTO get(Long id) {
        Optional<UniversityGroup> optional = groupRepository.findById(id);
        if (optional.isPresent()) {
            UniversityGroup universityGroup = optional.get();
            return GetGroupResponseDTO.builder()
                    .localDate(universityGroup.getLocalDate())
                    .name(universityGroup.getName()).build();
        } else {
            throw new IllegalArgumentException("передал не тот id");
        }


    }


}