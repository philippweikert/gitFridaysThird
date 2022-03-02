import {useState, useEffect} from "react"
import {ToDo} from "./model";
import ToDoElement from "./ToDoElement";
import ToDoForm from "./ToDoForm";

export default function ToDoList() {

    const [toDos, setToDos] = useState([] as Array<ToDo>)

    const fetchAll = () => {
      fetch('http://localhost:8080/todolist')
          .then(response => response.json())
          .then((toDosFromBackend: Array<ToDo>) => setToDos(toDosFromBackend));
    }

    const deleteChecked = () => {
        fetch('http://localhost:8080/todolist' ,
            {method: 'DELETE'})
            .then(response => response.json())
            .then((toDosFromBackend : Array<ToDo>) => setToDos(toDosFromBackend));
    }

    useEffect(() => {
        fetchAll();
    }, []);

    return (
        <div className={'todo-list'}>
            <div>
                <ToDoForm onToDoCreation={setToDos} />
            </div>
            <div>
                <button onClick={deleteChecked} >Arbeit ist vollbracht!</button>
            </div>
            <ul>
            {toDos.map(task => <li key={task.id}><ToDoElement toDoItem={task} onToDoDeletion={fetchAll} onToDoChange={setToDos}/></li>)}
            </ul>
        </div>
    )

}