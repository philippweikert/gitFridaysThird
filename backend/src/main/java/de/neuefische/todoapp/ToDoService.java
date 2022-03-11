package de.neuefische.todoapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;


    public void createToDo(ToDos toDos) {
        toDoRepo.save(toDos);
    }

    public Collection<ToDos> getToDos() {
        return toDoRepo.findAll()
                .stream()
                .sorted()
                .toList();
    }

    public Optional<ToDos> getToDos(String id) {
        return toDoRepo.findById(id);
    }

    public void deleteToDo(String id) {
        toDoRepo.deleteById(id);
    }

    public Optional<ToDos> changeToDo(String id, ToDos toDos) {
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
