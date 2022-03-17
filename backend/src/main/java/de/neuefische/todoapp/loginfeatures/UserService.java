package de.neuefische.todoapp.loginfeatures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User createUser (User appUser) {
        return userRepo.save(appUser);
    }
}
