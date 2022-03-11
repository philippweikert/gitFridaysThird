package de.neuefische.todoapp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class ToDoServiceTest {

    @Test
    void shouldAddNewToDo() {
        ToDos toDos1 = new ToDos();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        ToDoService toDoService = new ToDoService(repo);

        toDoService.createToDo(toDos1);

        verify(repo).save(toDos1);
    }

    @Test
    void shouldGetAllToDos(){
        ToDos toDos1 = new ToDos();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDos toDos2 = new ToDos();
        toDos2.setToDo("Putzen");
        toDos2.setStatus(ToDoStatus.Open);

        List<ToDos> toDosList = List.of(toDos1, toDos2);
        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findAll()).thenReturn(toDosList);

        ToDoService toDoService = new ToDoService(repo);

        Collection<ToDos> actual = toDoService.getToDos();

        assertThat(actual).isEqualTo(toDosList);
    }
    /*
    @Test
    void shouldGetMeOneToDo(){
        ToDos toDos1 = new ToDos();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findById(toDos1.getId())).thenReturn(Optional.of(toDos1));

        ToDoService toDoService = new ToDoService(repo);

        Optional<ToDos> actual = toDoService.getToDos(toDos1.getId());

        assertThat(actual).isEqualTo(toDos1);
    }*/

    @Test
    void shouldDeleteToDoById(){
        String id = "1898";

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        ToDoService toDoService = new ToDoService(repo);

        toDoService.deleteToDo(id);

        verify(repo).delete(id);
    }

    @Test
    void shouldChangeToDo(){
        ToDos toDos1 = new ToDos();
        toDos1.setId("1898");
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDos savedToDos = new ToDos();
        savedToDos.setId("1898");
        savedToDos.setToDo("Aufräumen");
        savedToDos.setStatus(ToDoStatus.Open);

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findById("1898")).thenReturn(Optional.of(toDos1));

        ToDoService toDoService = new ToDoService(repo);

        toDoService.changeToDo("1898", savedToDos);

        verify(repo).save(savedToDos);
    }
    /*
    @Test
    void shouldDeleteCheckedToDos() {
        ToDos toDos1 = new ToDos();
        toDos1.setToDo("ToDo1");
        toDos1.setStatus(ToDoStatus.Open);

        ToDos toDos2 = new ToDos();
        toDos2.setToDo("ToDo2");
        toDos2.setStatus(ToDoStatus.Done);

        ToDos toDos3 = new ToDos();
        toDos3.setToDo("ToDo3");
        toDos3.setStatus(ToDoStatus.Open);

        ToDos toDos4 = new ToDos();
        toDos4.setToDo("ToDo4");
        toDos4.setStatus(ToDoStatus.Done);

        List<ToDos> toDosList = List.of(toDos1, toDos2, toDos3, toDos4);
        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findAll()).thenReturn(toDosList);

        ToDoService toDoService = new ToDoService(repo);

        toDoService.deleteCheckedToDos();

        verify(repo).delete(toDos2.getId());
        verify(repo).delete(toDos4.getId());
    } */
}
