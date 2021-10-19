package ru.mattgroy.conspectusshare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.Models.Conspectus;

public interface ConspectusRepository extends JpaRepository<Conspectus, Long> {
}
