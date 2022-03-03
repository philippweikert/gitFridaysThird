import {Status, ToDo} from "./model";
import {useState} from "react";
import {useTranslation} from "react-i18next";


interface ToDoElementProps {
    toDoItem: ToDo;
    onToDoDeletion: () => void;
    onToDoChange: (toDoItems: Array<ToDo>) => void;
}

export default function ToDoElement(props: ToDoElementProps) {

    const {t} = useTranslation();

    const [toDoToEdit, setToDoToEdit] = useState(props.toDoItem.toDo);
    const [dateToEdit, setDateToEdit] = useState(props.toDoItem.date);
    const [editMode, setEditMode] = useState(false);

    const deleteToDo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todolist/${props.toDoItem.id}`,
            {
                method: 'DELETE'
            })
            .then(() => props.onToDoDeletion());
    };

    const fetchToEdit = (ToDoElement: ToDo) => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todolist/${props.toDoItem.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ToDoElement)
        })
            .then(response => response.json())
            .then((toDosFromBackend: Array<ToDo>) => {
                props.onToDoChange(toDosFromBackend);
                setEditMode(false);
            });

    }

    const editToDo = () => {
        fetchToEdit({
            id: props.toDoItem.id,
            toDo: toDoToEdit,
            date: dateToEdit,
            status: props.toDoItem.status
        })
    }
    const toggle = () => {
        const newStatus = props.toDoItem.status === Status.Open ? Status.Done : Status.Open;
        fetchToEdit({
            id: props.toDoItem.id,
            toDo: props.toDoItem.toDo,
            date: props.toDoItem.date,
            status: newStatus
        })
    }

    return (
        <div>
            {
                editMode
                    ?
                    <div>
                        <input type={'text'} value={toDoToEdit} onChange={event => setToDoToEdit(event.target.value)}/>
                        <input type={'text'} value={dateToEdit}
                               onChange={event2 => setDateToEdit(event2.target.value)}/>
                        <button onClick={editToDo}>Pass mich an!</button>
                    </div>
                    :
                    <div>
                        <span className={props.toDoItem.status === Status.Done ? 'selected' : ''}
                              onClick={toggle}>{props.toDoItem.toDo} </span>
                        <button onClick={() => setEditMode(true)}>{t('change')}</button>
                        <button onClick={deleteToDo}>{t('delete')}</button>
                    </div>

            }
                    </div>
    )
}

