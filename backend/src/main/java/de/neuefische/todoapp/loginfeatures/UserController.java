package de.neuefische.todoapp.loginfeatures;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todolist/createuser")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@RequestBody User appUser) {
        appUser.setUsername(passwordEncoder.encode(appUser.getUserpassword()));
        return userService.createUser(appUser);
    }
}
