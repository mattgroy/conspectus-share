package ru.mattgroy.conspectusshare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
