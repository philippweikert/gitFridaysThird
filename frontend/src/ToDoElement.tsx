import {Status, ToDo} from "./model";


interface ToDoElementProps{
    toDoItem: ToDo
    onToDoDeletion: () => void;
    onToDoChange: (toDoItems: Array<ToDo>) => void;
}

export default function ToDoElement (props: ToDoElementProps) {

    const deleteToDo = () => {
      fetch(`http://localhost:8080/todolist/${props.toDoItem.id}`,
          {method: 'DELETE'
          })
          .then(() => props.onToDoDeletion());
    };

    const toggle = () => {
        const newStatus = props.toDoItem.status === Status.Open ? Status.Done : Status.Open;

        fetch(`http://localhost:8080/todolist/${props.toDoItem.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: props.toDoItem.id,
                toDo: props.toDoItem.toDo,
                status: newStatus
            })
            })
            .then(response => response.json())
            .then((toDosFromBackend: Array<ToDo>) => props.onToDoChange(toDosFromBackend));
    }

    return (
        <div>
            <span className={props.toDoItem.status === Status.Done ? 'selected': ''} onClick={toggle}>{props.toDoItem.toDo} </span> <button onClick={deleteToDo}>Weg damit!</button>
        </div>
    )
}

