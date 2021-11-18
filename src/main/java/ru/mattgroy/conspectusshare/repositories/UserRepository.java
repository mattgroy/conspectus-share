package ru.mattgroy.conspectusshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mattgroy.conspectusshare.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = :email")
//    User findByEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByPrincipalId(String principalId);
}
