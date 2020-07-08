package kz.iitu.csse.group34.repositories;


import kz.iitu.csse.group34.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Long>{
    List<Courses> findAll();
    Optional<Courses> findById(Long id);

}