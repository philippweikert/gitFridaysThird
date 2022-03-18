package de.neuefische.todoapp.todofunctionalities;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;


    public void createToDo(ToDo toDos) {
        toDoRepo.save(toDos);
    }

    public Collection<ToDo> getToDos(String username) {
        return toDoRepo.findAllByUsername(username)
                .stream()
                .sorted()
                .toList();
    }

    public Optional<ToDo> getToDo(String id) {
        return toDoRepo.findById(id);
    }

    public void deleteToDo(String id) {
        toDoRepo.deleteById(id);
    }

    public Optional<ToDo> changeToDo(String id, ToDo toDos) {
        return toDoRepo.findById(id)
                .map(tD -> tD.updateToDo(toDos))
                .map(toDoRepo::save);
    }

    public void deleteCheckedToDos() {
        toDoRepo.findAll()
                .stream()
                .filter(toDos -> toDos.getStatus() == ToDoStatus.Done)
                .toList()
                .forEach(toDos -> toDoRepo.deleteById(toDos.getId()));
    }

}
