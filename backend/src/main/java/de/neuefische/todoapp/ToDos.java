package de.neuefische.todoapp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ToDos implements Comparable<ToDos> {

    private String id;
    private String toDo = "";
    private ToDoStatus status = ToDoStatus.Open;
    private String date = "";

    @Override
    public int compareTo(ToDos toDos){
        if (status == toDos.getStatus()){
            return 0;
        } else if (status == ToDoStatus.Open) {
            return -1;
        }
        return 1;
    }

    public ToDos updateToDo(ToDos toDos) {
        setToDo(toDos.getToDo());
        setDate(toDos.getDate());
        setStatus(toDos.getStatus());
        return this;
    }
}
