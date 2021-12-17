package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.Dto.FindConspectusDto;
import ru.mattgroy.conspectusshare.models.Conspectus;
import ru.mattgroy.conspectusshare.models.Subject;
import ru.mattgroy.conspectusshare.repositories.ConspectusRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConspectusServiceImpl implements ConspectusService {
    private static final String splitRegex = "[[ ]*|[,]*|[\\.]*|[:]*|[/]*|[!]*|[?]*|[+]*]+";

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
    public Conspectus createConspectus(@NotNull Conspectus request) {
        return conspectusRepository.save(request);
    }

    @Override
    @Transactional
    public void delete(@NotNull Long conspectusId) {
        conspectusRepository.deleteById(conspectusId);
    }

    @Override
    public List<Conspectus> find(FindConspectusDto findConspectusDto) {
        /*
        var conspectusTemplate = new Conspectus();
        var subjectTemplate = new Subject();
        if (findConspectusDto.getSubjectId() != null) subjectTemplate.setId(findConspectusDto.getSubjectId());
        if (findConspectusDto.getCourse() != null) subjectTemplate.setCourse(findConspectusDto.getCourse());
        if (findConspectusDto.getYear() != null) subjectTemplate.setYear(findConspectusDto.getYear());
        conspectusTemplate.setSubject(subjectTemplate);
        var example = Example.of(
                conspectusTemplate,
                ExampleMatcher.matching()
                        .withIgnoreNullValues()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return conspectusRepository.findAll(example);
        */

        return conspectusRepository
//                .findAll(findConspectusDto.getName(), findConspectusDto.getSubjectId(), findConspectusDto.getCourse(), findConspectusDto.getYear());
                .findByNameContainingIgnoreCase(findConspectusDto.getName())
                .stream()
                .filter(conspectus ->
//                        (findConspectusDto.getName().trim().isEmpty() || Arrays.stream(findConspectusDto.getName().toLowerCase().split(splitRegex)).anyMatch(conspectus.getName().toLowerCase()::contains)) &&
                        (findConspectusDto.getSubjectId() == null || conspectus.getSubject().getId().equals(findConspectusDto.getSubjectId())) &&
                        (findConspectusDto.getCourse() == null || conspectus.getSubject().getCourse() == findConspectusDto.getCourse()) &&
                        (findConspectusDto.getYear() == null || conspectus.getSubject().getYear() == findConspectusDto.getYear()))
                .collect(Collectors.toList());
    }
}
