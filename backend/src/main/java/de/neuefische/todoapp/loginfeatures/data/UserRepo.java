package de.neuefische.todoapp.loginfeatures.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<AppUser, String > {

    Optional<AppUser> findByUsername(String username);
}
