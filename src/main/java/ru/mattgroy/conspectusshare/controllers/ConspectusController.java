package ru.mattgroy.conspectusshare.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.Dto.SaveConspectusDto;
import ru.mattgroy.conspectusshare.models.Conspectus;
import ru.mattgroy.conspectusshare.models.Subject;
import ru.mattgroy.conspectusshare.models.User;
import ru.mattgroy.conspectusshare.services.ConspectusService;
import ru.mattgroy.conspectusshare.services.SubjectService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/conspectus")
@RequiredArgsConstructor
public class ConspectusController {
    @NonNull
    private ConspectusService conspectusService;
    @NonNull
    private SubjectService subjectService;

    @GetMapping("/register")
    public String register(Principal principal, Model model) {
        var conspectuses = conspectusService.findAll();
        model.addAttribute("conspectuses", conspectuses);
        model.addAttribute("userName", principal.getName());
        return "conspectus-registry";
    }

    @GetMapping(value = "/{conspectusId}", produces = APPLICATION_JSON_VALUE)
    public Conspectus findById(@PathVariable Long conspectusId) {
        return conspectusService.findById(conspectusId);
    }

    @GetMapping(value = "/save")
    public String getSaveConspectusPage(Model model) {
        model.addAttribute("conspectus", new SaveConspectusDto());
        model.addAttribute("subjects", subjectService.findAll());

        return "save-conspectus-form";
    }

    @PostMapping(value = "/save")
    public String create(SaveConspectusDto conspectus) {
        var newConspectus = new Conspectus();
        newConspectus.setConspectusMarkdown(conspectus.getConspectusMarkdown());
        var subject = new Subject();
        subject.setId(conspectus.getSubjectId());
        newConspectus.setSubject(subject);
        newConspectus.setName(conspectus.getName());
        var owner = new User();
        owner.setId(1l);
        newConspectus.setOwner(owner);
        conspectusService.createConspectus(newConspectus);
        return "redirect:/conspectus/register";
    }

    @GetMapping(value = "/file")
    public void export(@RequestParam("conspectusId") String conspectusId, HttpServletResponse response) throws IOException {
        var conspectus = conspectusService.findById(Long.parseLong(conspectusId) );
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition",String.format("attachment;filename=%s.txt", conspectus.getName()));
        ServletOutputStream out = response.getOutputStream();
        out.println(conspectus.getConspectusMarkdown());
        out.flush();
        out.close();
    }
}
