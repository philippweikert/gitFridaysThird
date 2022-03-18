import {FormEvent, useState} from "react";
import {useTranslation} from "react-i18next";
import axios from "axios";

export default function LoginElement() {

    const [newUsername, setNewUsername] = useState("")
    const [newUserPasswordOne, setNewUserPasswordOne] = useState("")
    const [newUserPasswordTwo, setNewUserPasswordTwo] = useState("")
    const [userName, setUsername] = useState("")
    const [userPassword, setUserPassword] = useState("")
    const [error, setError] = useState("")
    const [token, setToken] = useState("")
    const [checker, setChecker] = useState("")

    const {t} = useTranslation();

    const createNewUser = (event: FormEvent) => {
        event.preventDefault()
        setError('')
        if (!(newUserPasswordOne === newUserPasswordTwo) || newUserPasswordOne.length < 7) {
            setError("Das Passwort ist falsch oder zu kurz")
        } else {
            axios.post(`${process.env.REACT_APP_BASE_URL}/todolist/create`,
                {'username': newUsername, 'password': newUserPasswordOne})
                .then(response => response.data)
                .catch(e => {
                    if (e.response.status === 400) {
                        setError("Der Name wird schon genutzt")
                    } else {
                        setError(e.message)
                    }
                })
            setNewUsername('')
            setNewUserPasswordOne('')
            setNewUserPasswordTwo('')
        }
    }

    const logUserIn = (event : FormEvent) => {
        event.preventDefault()
        setError('')
        axios.post(`/todolist/login`, {'username':userName, 'password':userPassword})
            .then(response => response.data)
            .then(data => setToken(data.token))
            .catch(e => setError(e.message))
        setUsername('')
        setUserPassword('')
    }

    const getInfos = () => {
        axios.get(`/todolist/me`,
            {headers: {
                Authorization: `Bearer ${token}`,
                }}
        )
            .then(response => response.data)
            .then(data => setChecker(data.username))
            .catch(e => setError(e.message))
    }

    return (
        <div className= "LoginForm">
            <h3>Vergiss nie wieder etwas!</h3>
            <form onSubmit={createNewUser}>
                <input type="text" placeholder={'Wie soll ich dich nennen?'} value={newUsername} onChange={ev => setNewUsername(ev.target.value)}/>
                <input type="password" placeholder={'Gib hier dein Password ein!'} value={newUserPasswordOne} onChange={ev => setNewUserPasswordOne(ev.target.value)}/>
                <input type="password" placeholder={'Wiederhole hier dein Password!'} value={newUserPasswordTwo} onChange={ev => setNewUserPasswordTwo(ev.target.value)}/>
                <button type='submit'>Fütter mich mit deinen Daten!</button>
            </form>
            <h3>Naaa, was vergessen?!</h3>
            <form>
                <input type="text" placeholder={'Dein Nutzername'} value={userName} onChange={ev => setUsername(ev.target.value)}/>
                <input type="password" placeholder={'Dein Kennwort'} value={userPassword} onChange={ev => setUserPassword(ev.target.value)}/>
                <button type='submit'>Erinnere dich!</button>
            </form>
            {error && <h1>{error}</h1>}
            {token && <div>
                <button onClick={getInfos}>Hier geht es zu deinen Stützen!</button>
                <p>Herzlichen Glückwunsch: {checker && checker}, auf geht's!</p>
            </div>}
        </div>
    )
}
