package ru.example.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.university.model.UniversityGroup;

import java.util.List;

public interface GroupRepository extends JpaRepository<UniversityGroup, Long> {

    @Query("select u from UniversityGroup u")
    List<UniversityGroup> findAll();

    @Query("select u from UniversityGroup u where u.id = ?1 and u.name = ?2")
    UniversityGroup findByIdAndName(Long id, String name);

    @Query(value = "select u from UniversityGroup u where u.name like '%china%'")
    List<UniversityGroup> onlyChina();


}
