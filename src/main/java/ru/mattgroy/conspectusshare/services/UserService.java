package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import ru.mattgroy.conspectusshare.models.User;

import java.util.List;

public interface UserService {

    @NotNull
    List<User> findAll();

    @NotNull
    User findUserById(@NotNull Long userId);

    User createUser(@NotNull User request);

    boolean deleteUser(@NotNull Long userId);
}