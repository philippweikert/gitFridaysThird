package de.neuefische.todoapp.loginfeatures.controller;

import de.neuefische.todoapp.loginfeatures.Service.UserService;
import de.neuefische.todoapp.loginfeatures.data.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist/create")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createUser (@RequestBody AppUser appUser) {
        appUser.setUserpassword(passwordEncoder.encode(appUser.getUserpassword()));
        return userService.createUser(appUser);
    }
}
