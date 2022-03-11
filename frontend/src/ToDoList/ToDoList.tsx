import {useState, useEffect} from "react"
import {ToDo} from "../model";
import ToDoElement from "../ToDoElement/ToDoElement";
import ToDoForm from "../ToDoForm/ToDoForm";
import {useTranslation} from "react-i18next";

export default function ToDoList() {

    const {t} = useTranslation();

    const [toDos, setToDos] = useState([] as Array<ToDo>)
    const [errorMessage, setErrorMessage] = useState('')


    const fetchAll = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todolist`)
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                }
                throw new Error("Das war wohl nix!")
            })
            .then((toDosFromBackend: Array<ToDo>) => setToDos(toDosFromBackend))
            .catch((ev: Error) => setErrorMessage(ev.message))
    }

    const deleteChecked = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todolist`,
            {method: 'DELETE'})
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                }
                throw new Error("Da stimmt doch was nicht")
            })
            .then((toDosFromBackend: Array<ToDo>) => setToDos(toDosFromBackend))
            .catch((ev: Error) => setErrorMessage(ev.message))
    }

    useEffect(() => {
        fetchAll();
    }, []);

    useEffect(() => {
        setTimeout(() => {
            setErrorMessage('')
        }, 20000)
    })

    return (
        <div className={'todo-list'}>
            <div>
                <ToDoForm onToDoCreation={setToDos}/>
            </div>
            <div>
                <button onClick={deleteChecked}>{t('done-button')}</button>
            </div>
            <ul>
                {toDos.map(task => <li key={task.id}><ToDoElement toDoItem={task} onToDoDeletion={fetchAll}
                                                                  onToDoChange={setToDos}/></li>)}
            </ul>
            <div className="get-error">{errorMessage}</div>
        </div>
    )

}
