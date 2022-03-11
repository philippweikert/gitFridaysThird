package de.neuefische.todoapp;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ToDoRepo extends MongoRepository<ToDos, String>{
}
