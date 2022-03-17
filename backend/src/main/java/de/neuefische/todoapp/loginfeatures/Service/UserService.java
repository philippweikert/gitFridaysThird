package de.neuefische.todoapp.loginfeatures.Service;

import de.neuefische.todoapp.loginfeatures.data.AppUser;
import de.neuefische.todoapp.loginfeatures.data.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public AppUser createUser (AppUser appUser) {
        return userRepo.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
