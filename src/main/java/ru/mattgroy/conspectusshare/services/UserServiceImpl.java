package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.models.User;
import ru.mattgroy.conspectusshare.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @NonNull
    private UserRepository userRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public User findById(@NotNull Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public User createUser(@NotNull User request) {
        return userRepository.save(request);
    }

    @Override
    @Transactional
    public void delete(@NotNull Long userId) {
        userRepository.deleteById(userId);
    }
}
