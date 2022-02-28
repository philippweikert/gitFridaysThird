package de.neuefische.todoapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/todolist")
@CrossOrigin
@RequiredArgsConstructor
public class ToDoController {

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }

    @GetMapping
    public Collection<ToDos> getToDos() {
        return toDoService.getToDos();
    }

    @GetMapping("/{id}")
    public ToDos getToDo(@PathVariable String id){
        return toDoService.getToDos(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<ToDos> createToDo (@RequestBody ToDos toDos){
        toDoService.createToDo(toDos);
        return toDoService.getToDos();
    }

    @DeleteMapping("/{id}")
    public Collection<ToDos> deleteTodo(@PathVariable String id) {
        toDoService.deleteToDo(id);
        return toDoService.getToDos();
    }

    @PutMapping("/{id}")
    public Collection<ToDos> changeTodo(@PathVariable String id, @RequestBody ToDos toDos) {
        toDoService.changeToDo(id, toDos);
        return toDoService.getToDos();
    }

}
