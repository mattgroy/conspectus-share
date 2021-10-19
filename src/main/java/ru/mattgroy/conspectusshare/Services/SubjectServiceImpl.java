package ru.mattgroy.conspectusshare.Services;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.Models.Subject;
import ru.mattgroy.conspectusshare.Repositories.SubjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{
    @NonNull
    private SubjectRepository subjectRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return new ArrayList<>(subjectRepository.findAll());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public Subject findById(@NotNull Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject " + subjectId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public Subject createUser(@NotNull Subject request) {
        return subjectRepository.save(request);
    }

    @Override
    @Transactional
    public void delete(@NotNull Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
