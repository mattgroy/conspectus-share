package ru.mattgroy.conspectusshare.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.models.User;
import ru.mattgroy.conspectusshare.services.UserService;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(Principal principal) {
//        ((OAuth2AuthenticationToken) principal).getPrincipal().getAttribute("email")
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User findById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User request) {
        return userService.createUser(request);
    }
}
