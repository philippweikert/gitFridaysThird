import {useState, useEffect} from "react"
import {ToDo} from "./model";
import ToDoElement from "./ToDoElement";
import ToDoForm from "./ToDoForm";
import {useTranslation} from "react-i18next";

export default function ToDoList() {

    const {t} = useTranslation();

    const [toDos, setToDos] = useState([] as Array<ToDo>)

    const fetchAll = () => {
      fetch(`${process.env.REACT_APP_BASE_URL}/todolist`)
          .then(response => response.json())
          .then((toDosFromBackend: Array<ToDo>) => setToDos(toDosFromBackend));
    }

    const deleteChecked = () => {
        fetch(`${process.env.REACT_APP_URL}/todolist` ,
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
                <button onClick={deleteChecked} >{t('done-button')}</button>
            </div>
            <ul>
            {toDos.map(task => <li key={task.id}><ToDoElement toDoItem={task} onToDoDeletion={fetchAll} onToDoChange={setToDos}/></li>)}
            </ul>
        </div>
    )

}