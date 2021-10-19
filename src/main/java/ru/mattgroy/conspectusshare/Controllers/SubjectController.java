package ru.mattgroy.conspectusshare.Controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.Models.Subject;
import ru.mattgroy.conspectusshare.Services.SubjectService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {
    @NonNull
    private SubjectService subjectService;

    @GetMapping(value = "/{subjectId}", produces = APPLICATION_JSON_VALUE)
    public Subject findById(@PathVariable Long subjectId) {
        return subjectService.findById(subjectId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Subject create(@RequestBody Subject request) {
        return subjectService.createUser(request);
    }
}
