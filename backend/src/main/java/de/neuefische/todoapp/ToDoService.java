package de.neuefische.todoapp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepo toDoRepo;

    
    public void createToDo (ToDos toDos){
        toDoRepo.save(toDos);
    }

    public Collection<ToDos> getToDos(){
        return toDoRepo.findAll()
                .stream()
                .sorted()
                .toList();
    }

   public ToDos getToDos (String id) {
        return toDoRepo.findById(id);
    }

    public void deleteToDo (String id) {
        toDoRepo.delete(id);
    }

    public void changeToDo (String id, ToDos changedToDo) {
        ToDos toDos = toDoRepo.findById(id);

        toDos.setStatus (changedToDo.getStatus());
        toDos.setToDo(changedToDo.getToDo());

        toDoRepo.save(toDos);
    }

    public void deleteCheckedToDos(){
        toDoRepo.findAll()
                .stream()
                .filter(toDos -> toDos.getStatus() == ToDoStatus.Done)
                .toList()
                .forEach(toDos -> toDoRepo.delete(toDos.getId()));
    }

}
