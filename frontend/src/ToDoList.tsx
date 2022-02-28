import {useState, useEffect} from "react"
import {ToDo} from "./model";
import ToDoElement from "./ToDoElement";
import ToDoForm from "./ToDoForm";

export default function ToDoList() {

    const [toDos, setToDos] = useState([] as Array<ToDo>)

    const fetchAll = () => {
      fetch('http://localhost:8080/todolist')
          .then(response => response.json())
          .then((toDosFromBackend: Array<ToDo>) => setToDos(toDosFromBackend)
          )
    }

    useEffect(() => {
        fetchAll();
    }, []);

    return (
        <div>
            <ToDoForm onToDoCreation={setToDos}/>
            {toDos.map(task => <ToDoElement key={task.id} toDoItem={task} onToDoDeletion={fetchAll} onToDoChange={setToDos}/>)}
        </div>
    )

}