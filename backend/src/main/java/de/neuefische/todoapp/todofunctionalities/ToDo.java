package de.neuefische.todoapp.todofunctionalities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document

public class ToDo implements Comparable<ToDo> {

    @Id
    private String id;
    private String toDo = "";
    private ToDoStatus status = ToDoStatus.Open;
    private String date = "";
    private String username;

    @Override
    public int compareTo(ToDo toDos){
        if (status == toDos.getStatus()){
            return 0;
        } else if (status == ToDoStatus.Open) {
            return -1;
        }
        return 1;
    }

    public ToDo updateToDo(ToDo toDos) {
        setToDo(toDos.getToDo());
        setDate(toDos.getDate());
        setStatus(toDos.getStatus());
        return this;
    }
}
