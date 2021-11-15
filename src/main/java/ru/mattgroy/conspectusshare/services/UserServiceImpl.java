package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.models.GoogleUser;
import ru.mattgroy.conspectusshare.models.User;
import ru.mattgroy.conspectusshare.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @NonNull
    @Autowired
    private UserRepository userRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public User findUserById(@NotNull Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User " + userId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public User createUser(@NotNull User user) {
        return userRepository.save(user);
    }

//    @Override
//    @Transactional
//    public void deleteUser(@NotNull Long userId) {
//        userRepository.deleteById(userId);
//    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public void processOAuthPostLogin(GoogleUser googleUser) {
        User existUser = userRepository.findByEmail(googleUser.getEmail());

        if (existUser == null) {
            User newUser = new User();
            newUser.setFirstName(googleUser.getGivenName());
            newUser.setLastName(googleUser.getFamilyName());
            newUser.setEmail(googleUser.getEmail());
            createUser(newUser);
        }
    }
}
