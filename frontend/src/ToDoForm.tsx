import {useState} from "react"
import {ToDo} from "./model";
import {useTranslation} from "react-i18next";

interface ToDoFormProps {
    onToDoCreation: (toDoItems: Array<ToDo>) => void;
}

export default function ToDoForm (props: ToDoFormProps) {

    const [toDo, setToDo] = useState('');
    const [date, setDate] = useState('');

    const{t} =useTranslation();

    const addToDo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todolist`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                toDo: toDo,
                date: date,
            })
        })
            .then(response => response.json())
            .then((toDosFromBackend: Array<ToDo>) => {
                setToDo('');
                setDate('');
                props.onToDoCreation(toDosFromBackend);
            });
    }

    return (
        <div>
            <input type={'text'} placeholder={t('task')} value={toDo} onChange = {event => setToDo(event.target.value)}/>
            <input type={'text'} placeholder={t('date')} value={date} onChange={event2 => setDate(event2.target.value)}/>
            <button onClick={addToDo} className={'send.button'}>{t('send')}</button>
        </div>
    )
}