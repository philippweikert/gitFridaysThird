import {useEffect, useState} from "react";
import {ToDo} from "./model";
import {Link, useParams} from "react-router-dom"


export default function ToDoDetails() {
    const [toDoDetail, setToDoDetail] = useState({} as ToDo)
    const params = useParams()


    useEffect(() => {
        fetch('http://localhost:8080/todolist' + params.id)
            .then(response => response.json())
            .then((toDo: ToDo) => setToDoDetail(toDo))
    }, [])

    return (

        <div>
            {toDoDetail.date &&
                <div>
                    <ul>
                        <li>Enddatum: {toDoDetail.date}</li>
                    </ul>
                    <Link className='back-to-overview' to='/ToDoList'>Zurück</Link>
                </div>
            }
        </div>
    )
}
