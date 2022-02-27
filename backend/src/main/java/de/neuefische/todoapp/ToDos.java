package de.neuefische.todoapp;

import java.util.Objects;
import java.util.UUID;


public class ToDos {

    private String toDo;
    private String id;
    private ToDoStatus status = ToDoStatus.Open;

    public ToDos(String toDo, String id) {
        this.toDo = toDo;
        this.id = UUID.randomUUID().toString();

    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getToDoId() {
        return id;
    }

    public ToDoStatus getStatus() {
        return status;
    }

    public void setStatus(ToDoStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDos toDos = (ToDos) o;
        return Objects.equals(toDo, toDos.toDo) && Objects.equals(id, toDos.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toDo, id);
    }
}
