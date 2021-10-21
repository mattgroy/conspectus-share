package ru.mattgroy.conspectusshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
