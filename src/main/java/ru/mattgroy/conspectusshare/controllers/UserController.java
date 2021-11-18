package ru.mattgroy.conspectusshare.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.mattgroy.conspectusshare.models.CustomOAuth2User;
import ru.mattgroy.conspectusshare.models.User;
import ru.mattgroy.conspectusshare.services.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    private UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(@AuthenticationPrincipal CustomOAuth2User oauth2User) {
//        oauth2User.getPrincipalId();
        return userService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User request) {
        return userService.createUser(request);
    }
}
