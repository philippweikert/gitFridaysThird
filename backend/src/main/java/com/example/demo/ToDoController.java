package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;

    }

    @GetMapping
    public List<ToDos> getToDos() {
        return toDoService.getToDos();
    }

    @GetMapping("/{deadLineDate")
    public List <ToDos> getToDo(@PathVariable String deadLineDate){
        return toDoService.getToDos(deadLineDate);
    }

    @PostMapping
    public void createToDo (@RequestBody ToDos toDos){
        toDoService.createToDo(toDos);
    }
}
