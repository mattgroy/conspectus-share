package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import ru.mattgroy.conspectusshare.models.Conspectus;

import java.util.List;

public interface ConspectusService {
    @NotNull
    List<Conspectus> findAll();

    @NotNull
    Conspectus findById(@NotNull Long conspectusId);

    @NotNull
    Conspectus createUser(@NotNull Conspectus request);

    void delete(@NotNull Long userId);
}
