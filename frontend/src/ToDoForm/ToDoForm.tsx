import {useEffect, useState} from "react"
import {ToDo} from "../model";
import {useTranslation} from "react-i18next";

interface ToDoFormProps {
    onToDoCreation: (toDoItems: Array<ToDo>) => void;
}

export default function ToDoForm (props: ToDoFormProps) {

    //const [toDo, setToDo] = useState('');
    //const [date, setDate] = useState('');
    const [errorMessage, setErrorMessage] =useState('');
    const [toDo, setToDo] = useState(localStorage.getItem("toDo") ?? '');
    const [date, setDate] = useState(localStorage.getItem("date") ?? '');

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
            .then(response => {
                if (response.status === 201) {
                    return response.json()
                }
                throw new Error('Tach Post!')
            })
            .then((toDosFromBackend: Array<ToDo>) => {
                setToDo('');
                setDate('');
                props.onToDoCreation(toDosFromBackend);
            })
            .catch((ev: Error) => setErrorMessage(ev.message))
    }

    useEffect(() =>{
        localStorage.setItem("toDo", toDo)
    }, [toDo])

    useEffect(() =>{
        localStorage.setItem("date", date)
    }, [date])

    return (
        <div>
            <input type={'text'} placeholder={t('task')} value={toDo} onChange = {event => setToDo(event.target.value)}/>
            <input type={'text'} placeholder={t('date')} value={date} onChange = {event2 => setDate(event2.target.value)}/>
            <button onClick={addToDo} className={'send.button'}>{t('send')}</button>
            <div>{errorMessage}</div>
        </div>
    )
}