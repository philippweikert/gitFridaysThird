package de.neuefische.todoapp;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class ToDoRepo {

    private Map<String, ToDos> toDosList = new HashMap<>();

    public void save (ToDos toDo) {
        toDosList.put(toDo.getId(), toDo);
    }
    public Collection<ToDos> findAll(){
        return toDosList.values();
    }

    public ToDos findById (String id) {
        return toDosList.get(id);
    }

    public void delete(String id) {
        toDosList.remove(id);
    }

}
