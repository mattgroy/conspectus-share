package ru.mattgroy.conspectusshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.models.Conspectus;

import java.util.List;

public interface ConspectusRepository extends JpaRepository<Conspectus, Long> {
    List<Conspectus> findByNameContainingIgnoreCase(String name);
}
