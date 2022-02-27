package de.neuefische.todoapp;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ToDoService {

    private ToDoRepo toDoRepo;

    public ToDoService(ToDoRepo toDoRepo){
        this.toDoRepo = toDoRepo;
    }
    
    public void createToDo (ToDos toDos){
        toDoRepo.save(toDos);
    }

    public Collection<ToDos> getToDos(){
        return toDoRepo.findAll();
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

}
