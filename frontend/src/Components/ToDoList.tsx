import {useEffect, useState} from "react";
import ToDoElement from "./ToDoElement";

export default function ToDoList () {

    const [toDos, setToDos] = useState([])

    useEffect(() => {
        fetch(`http://localhost:8080/todolist` , {method: 'GET'})
            .then(response => response.json())
            .then(data => setToDos(data))


    }, []);
    const requestBody = {
        toDos: toDos
    }
    const getToDoById = () => {
        fetch(`http://localhost:8080/todolist`, {method: 'POST', body:JSON.stringify(requestBody)})
            .then(response => response.json())
            .then(responseBody : Array<string>) => set
    }

    return (
        <div>
            {toDos.map(todo => <p>todo.toDo</p>)}
        </div>
                )
}