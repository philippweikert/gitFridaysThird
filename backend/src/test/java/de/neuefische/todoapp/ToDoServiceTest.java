package de.neuefische.todoapp;

import de.neuefische.todoapp.todofunctionalities.ToDo;
import de.neuefische.todoapp.todofunctionalities.ToDoRepo;
import de.neuefische.todoapp.todofunctionalities.ToDoService;
import de.neuefische.todoapp.todofunctionalities.ToDoStatus;
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
        ToDo toDos1 = new ToDo();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        ToDoService toDoService = new ToDoService(repo);

        toDoService.createToDo(toDos1);

        verify(repo).save(toDos1);
    }

   /* @Test
    void shouldGetAllToDos(){
        ToDo toDos1 = new ToDo();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDo toDos2 = new ToDo();
        toDos2.setToDo("Putzen");
        toDos2.setStatus(ToDoStatus.Open);

        List<ToDo> toDosList = List.of(toDos1, toDos2);
        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findAll()).thenReturn(toDosList);

        ToDoService toDoService = new ToDoService(repo);

        Collection<ToDo> actual = toDoService.getToDos();

        assertThat(actual).isEqualTo(toDosList);
    } */
    /*
    @Test
    void shouldGetMeOneToDo(){
        ToDo toDos1 = new ToDo();
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findById(toDos1.getId())).thenReturn(Optional.of(toDos1));

        ToDoService toDoService = new ToDoService(repo);

        Optional<ToDo> actual = toDoService.getToDos(toDos1.getId());

        assertThat(actual).isEqualTo(toDos1);
    }*/

    @Test
    void shouldDeleteToDoById(){
        String id = "1898";

        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        ToDoService toDoService = new ToDoService(repo);

        toDoService.deleteToDo(id);

        verify(repo).deleteById(id);
    }

    @Test
    void shouldChangeToDo(){
        ToDo toDos1 = new ToDo();
        toDos1.setId("1898");
        toDos1.setToDo("Aufräumen");
        toDos1.setStatus(ToDoStatus.Open);

        ToDo savedToDos = new ToDo();
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
        ToDo toDos1 = new ToDo();
        toDos1.setToDo("ToDo1");
        toDos1.setStatus(ToDoStatus.Open);

        ToDo toDos2 = new ToDo();
        toDos2.setToDo("ToDo2");
        toDos2.setStatus(ToDoStatus.Done);

        ToDo toDos3 = new ToDo();
        toDos3.setToDo("ToDo3");
        toDos3.setStatus(ToDoStatus.Open);

        ToDo toDos4 = new ToDo();
        toDos4.setToDo("ToDo4");
        toDos4.setStatus(ToDoStatus.Done);

        List<ToDo> toDosList = List.of(toDos1, toDos2, toDos3, toDos4);
        ToDoRepo repo = Mockito.mock(ToDoRepo.class);
        Mockito.when(repo.findAll()).thenReturn(toDosList);

        ToDoService toDoService = new ToDoService(repo);

        toDoService.deleteCheckedToDos();

        verify(repo).delete(toDos2.getId());
        verify(repo).delete(toDos4.getId());
    } */
}
