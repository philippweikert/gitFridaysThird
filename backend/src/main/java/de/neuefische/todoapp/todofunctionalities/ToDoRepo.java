package de.neuefische.todoapp.todofunctionalities;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface ToDoRepo extends MongoRepository<ToDo, String>{

    List<ToDo> findAllByUsername(String username);

}
