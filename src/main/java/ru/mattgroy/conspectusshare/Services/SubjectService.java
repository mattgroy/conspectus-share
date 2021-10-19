package ru.mattgroy.conspectusshare.Services;

import com.sun.istack.NotNull;
import ru.mattgroy.conspectusshare.Models.Subject;

import java.util.List;

public interface SubjectService {
    @NotNull
    List<Subject> findAll();

    @NotNull
    Subject findById(@NotNull Long subjectId);

    @NotNull
    Subject createUser(@NotNull Subject request);

    void delete(@NotNull Long userId);
}
