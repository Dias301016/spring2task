package kz.iitu.csse.group34.repositories;

import kz.iitu.csse.group34.entities.Groups;
import kz.iitu.csse.group34.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Students, Long> {
    List<Students> findAll();
    Optional<Students> findById(Long id);

}