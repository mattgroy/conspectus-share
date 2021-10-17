package ru.mattgroy.conspectusshare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShareController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

}