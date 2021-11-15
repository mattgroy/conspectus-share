package ru.mattgroy.conspectusshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.models.Conspectus;

public interface ConspectusRepository extends JpaRepository<Conspectus, Long> {
}
