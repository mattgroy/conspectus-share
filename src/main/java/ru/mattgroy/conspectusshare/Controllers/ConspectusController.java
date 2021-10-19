package ru.mattgroy.conspectusshare.Controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.Models.Conspectus;
import ru.mattgroy.conspectusshare.Services.ConspectusService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/conspectuses")
@RequiredArgsConstructor
public class ConspectusController {
    @NonNull
    private ConspectusService conspectusService;

    @GetMapping(value = "/{conspectusId}", produces = APPLICATION_JSON_VALUE)
    public Conspectus findById(@PathVariable Long conspectusId) {
        return conspectusService.findById(conspectusId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Conspectus create(@RequestBody Conspectus request) {
        return conspectusService.createUser(request);
    }
}
