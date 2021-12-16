package ru.mattgroy.conspectusshare.services;

import com.sun.istack.NotNull;
import org.springframework.transaction.annotation.Transactional;
import ru.mattgroy.conspectusshare.Dto.FindConspectusDto;
import ru.mattgroy.conspectusshare.models.Conspectus;

import java.util.List;

public interface ConspectusService {
    @NotNull
    List<Conspectus> findAll();

    @NotNull
    Conspectus findById(@NotNull Long conspectusId);

    @NotNull
    Conspectus createConspectus(@NotNull Conspectus request);

    void delete(@NotNull Long userId);

    @NotNull
    List<Conspectus> find(FindConspectusDto findConspectusDto);
}
