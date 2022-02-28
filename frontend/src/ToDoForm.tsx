import {useState} from "react"
import {ToDo} from "./model";

interface ToDoFormProps {
    onToDoCreation: (toDoItems: Array<ToDo>) => void;
}

export default function ToDoForm (props: ToDoFormProps) {

    const [toDo, setToDo] = useState('');

    const addToDo = () => {
        fetch('http://localhost:8080/todolist', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                toDo: toDo,
            })
        })
            .then(response => response.json())
            .then((toDosFromBackend: Array<ToDo>) => props.onToDoCreation(toDosFromBackend))
    }

    return (
        <div>
            <input type={'text'} placeholder={'Was ist zu tun?'} value={toDo} onChange = {event => setToDo(event.target.value)}/>
            <button onClick={addToDo}>Arbeit! Arbeit!</button>
        </div>
    )
}