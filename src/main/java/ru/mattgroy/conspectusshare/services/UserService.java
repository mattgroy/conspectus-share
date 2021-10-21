package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import ru.mattgroy.conspectusshare.models.User;

import java.util.List;

public interface UserService {

    @NotNull
    List<User> findAll();

    @NotNull
    User findById(@NotNull Long userId);

    @NotNull
    User createUser(@NotNull User request);

    void delete(@NotNull Long userId);
}