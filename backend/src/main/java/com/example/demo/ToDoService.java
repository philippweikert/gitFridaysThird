package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private ToDoRepo toDoRepo;

    public ToDoService(ToDoRepo toDoRepo){
        this.toDoRepo = toDoRepo;
    }

    public List<ToDos> getToDos(){
        return toDoRepo.getToDos();
    }

    public void createToDo (ToDos toDos){
        toDoRepo.createToDo(toDos);
    }

    public List <ToDos> getToDos(String deadLineDate) {
        return toDoRepo.getToDos(deadLineDate);
    }
}
