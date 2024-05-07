import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.university.dto.request.GroupRequestAddDTO;
import ru.example.university.dto.response.GetGroupResponseDTO;
import ru.example.university.model.UniversityGroup;
import ru.example.university.repository.GroupRepository;
import ru.example.university.service.impl.GroupServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    public void testAdd() {
        // Given
        GroupRequestAddDTO groupRequestAddDTO = new GroupRequestAddDTO();
        groupRequestAddDTO.setName("Test Group");

        UniversityGroup universityGroup = new UniversityGroup();
        universityGroup.setName("Test Group");
        universityGroup.setLocalDate(LocalDate.now());
        when(groupRepository.save(any(UniversityGroup.class))).thenReturn(universityGroup);
        groupService.add(groupRequestAddDTO);
        verify(groupRepository, times(1)).save(any(UniversityGroup.class));
    }
    @Test
    void testGetGroupForIdAndName_Success() {
        // Arrange
        UniversityGroup group = new UniversityGroup();
        group.setId(1L);
        group.setName("Group A");
        group.setLocalDate(LocalDate.now());

        when(groupRepository.findByIdAndName(anyLong(), anyString())).thenReturn(group);

        // Act
        GetGroupResponseDTO responseDTO = groupService.getGroupForIdAndName(1L, "Group A");

        // Assert
        assertEquals(1L, responseDTO.getId());
        assertEquals("Group A", responseDTO.getName());
        assertEquals(LocalDate.now(), responseDTO.getLocalDate());
    }

    @Test
    void testGetGroupForIdAndName_GroupNotFound() {
        // Arrange
        when(groupRepository.findByIdAndName(anyLong(), anyString())).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> groupService.getGroupForIdAndName(1L, "Group A"));
    }
}
