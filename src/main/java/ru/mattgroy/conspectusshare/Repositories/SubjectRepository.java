package ru.mattgroy.conspectusshare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.Models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
