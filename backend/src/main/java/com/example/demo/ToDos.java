package com.example.demo;

import java.util.Objects;

public class ToDos {

    private String toDo;
    private String deadLineDate;

    public ToDos(String toDo, String deadLineDate) {
        this.toDo = toDo;
        this.deadLineDate = deadLineDate;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public String getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDos toDos = (ToDos) o;
        return Objects.equals(toDo, toDos.toDo) && Objects.equals(deadLineDate, toDos.deadLineDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toDo, deadLineDate);
    }

}
