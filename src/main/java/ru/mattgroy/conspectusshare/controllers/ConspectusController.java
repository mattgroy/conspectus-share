package ru.mattgroy.conspectusshare.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.Dto.FindConspectusDto;
import ru.mattgroy.conspectusshare.Dto.SaveConspectusDto;
import ru.mattgroy.conspectusshare.models.Conspectus;
import ru.mattgroy.conspectusshare.models.CustomOAuth2User;
import ru.mattgroy.conspectusshare.models.Subject;
import ru.mattgroy.conspectusshare.services.ConspectusService;
import ru.mattgroy.conspectusshare.services.SubjectService;
import ru.mattgroy.conspectusshare.services.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/conspectus")
@RequiredArgsConstructor
public class ConspectusController {
    @NonNull
    private ConspectusService conspectusService;
    @NonNull
    private SubjectService subjectService;
    @NonNull
    private UserService userService;

    @GetMapping("/register")
    public String register(@AuthenticationPrincipal CustomOAuth2User oauth2User, Model model) {
        var conspectuses = conspectusService.findAll();
        model.addAttribute("conspectuses", conspectuses);
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("conspectusSearchModel", new FindConspectusDto());
        var subjects = subjectService.findAll();
        var emptySubject = new Subject();
        emptySubject.setId(null);
        emptySubject.setName("Выберите предмет");
        subjects.add(0, emptySubject);
        model.addAttribute("subjects", subjects);

        return "conspectus-registry";
    }

    @GetMapping(value = "/{conspectusId}", produces = APPLICATION_JSON_VALUE)
    public Conspectus findById(@PathVariable Long conspectusId) {
        return conspectusService.findById(conspectusId);
    }

    @GetMapping(value = "/save")
    public String getSaveConspectusPage(Model model) {
        model.addAttribute("conspectus", new SaveConspectusDto());
        var subjects = subjectService.findAll();
        var emptySubject = new Subject();
        emptySubject.setId(null);
        emptySubject.setName("Выберите предмет");
        subjects.add(0, emptySubject);
        model.addAttribute("subjects", subjects);

        return "save-conspectus-form";
    }

    @PostMapping(value = "/save")
    public String create(@AuthenticationPrincipal CustomOAuth2User oauth2User, SaveConspectusDto conspectus) {
        var newConspectus = new Conspectus();
        newConspectus.setConspectusMarkdown(conspectus.getConspectusMarkdown());
        var subject = new Subject();
        subject.setId(conspectus.getSubjectId());
        newConspectus.setSubject(subject);
        newConspectus.setName(conspectus.getName());
        var owner = userService.findByPrincipalId(oauth2User.getPrincipalId());
        newConspectus.setOwner(owner);
        conspectusService.createConspectus(newConspectus);
        return "redirect:/conspectus/register";
    }

    @PostMapping(value = "/find")
    public String find(@AuthenticationPrincipal CustomOAuth2User oauth2User, Model model, FindConspectusDto searchModel) {
        var conspectuses = conspectusService.find(searchModel);
        model.addAttribute("conspectuses", conspectuses);
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("conspectusSearchModel", new FindConspectusDto());
        var subjects = subjectService.findAll();
        var emptySubject = new Subject();
        emptySubject.setId(null);
        emptySubject.setName("Выберите предмет");
        subjects.add(0, emptySubject);
        model.addAttribute("subjects", subjects);
        return "conspectus-registry";
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
