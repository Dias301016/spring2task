package kz.iitu.csse.group34.repositories;

import kz.iitu.csse.group34.entities.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    List<Groups> findAll();
    Optional<Groups> findById(Long id);

}

