package ru.mattgroy.conspectusshare.Services;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.Models.Conspectus;
import ru.mattgroy.conspectusshare.Repositories.ConspectusRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConspectusServiceImpl implements ConspectusService{
    @NonNull
    private ConspectusRepository conspectusRepository;

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public List<Conspectus> findAll() {
        return new ArrayList<>(conspectusRepository.findAll());
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public Conspectus findById(@NotNull Long conspectusId) {
        return conspectusRepository.findById(conspectusId)
                .orElseThrow(() -> new EntityNotFoundException("Conspectus " + conspectusId + " is not found"));
    }

    @NotNull
    @Override
    @Transactional
    public Conspectus createUser(@NotNull Conspectus request) {
        return conspectusRepository.save(request);
    }

    @Override
    @Transactional
    public void delete(@NotNull Long conspectusId) {
        conspectusRepository.deleteById(conspectusId);
    }
}
