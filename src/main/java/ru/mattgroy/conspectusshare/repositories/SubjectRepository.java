package ru.mattgroy.conspectusshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
