package de.neuefische.todoapp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor

public class ToDos implements Comparable<ToDos> {

    private String id = UUID.randomUUID().toString();
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

}
