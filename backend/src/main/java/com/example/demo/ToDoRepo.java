package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ToDoRepo {

    private List<ToDos> toDosList = new ArrayList<>();

    public List<ToDos> getToDos(){
        return toDosList;
    }

    public void createToDo (ToDos toDos) {
        toDosList.add(toDos);
    }

    public List<ToDos> getToDos(String deadLineDate) {
       return toDosList.stream().filter(dld -> dld.getDeadLineDate().equals(deadLineDate)).toList();
    }
}
