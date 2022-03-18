package de.neuefische.todoapp.todofunctionalities;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/todolist")
@CrossOrigin
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;


    @GetMapping
    public Collection<ToDo> getToDos(Principal principal) {
        return toDoService.getToDos(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDo(@PathVariable String id){
        return ResponseEntity.of(toDoService.getToDo(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<ToDo> createToDo (@RequestBody ToDo toDos, Principal principal){
        toDos.setUsername(principal.getName());
        toDoService.createToDo(toDos);
        return toDoService.getToDos(principal.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteToDo (@PathVariable String id) {
        toDoService.deleteToDo(id);
    }

    @PutMapping("/{id}")
    public Collection<ToDo> changeToDo (@PathVariable String id, @RequestBody ToDo toDos, Principal principal) {
        toDoService.changeToDo(id, toDos);
        return toDoService.getToDos(principal.getName());
    }

    @DeleteMapping()
    public Collection<ToDo>deleteToDo(Principal principal) {
        toDoService.deleteCheckedToDos();
        return toDoService.getToDos(principal.getName());
    }
}
